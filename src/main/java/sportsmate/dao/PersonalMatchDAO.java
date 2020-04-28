package sportsmate.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class PersonalMatchDAO extends DAO {
  private Connection conn;
  private String sql;
  private PreparedStatement pStatement;
  private int pmatch_id;
  private int player_id;
  private int pmg_id;
  private String pmg_name;
  private String pmg_location;
  private Date game_date;
  private Time start_at;
  private Time end_at;
  private int game_type;
  private int num_initial_players;
  private int num_players_joined;

  public void createPersonalMatch(int userID, int gym_id, String game_date, String startAt,
      String endAt, String game_type, int num_initial_players) {

    try {
      conn = getConnection();

      SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
      sdf.setTimeZone(TimeZone.getTimeZone("EST"));
      //java.sql.Date sqlDate = new java.sql.Date(date.getTime());
      java.util.Date date = sdf.parse(game_date);
      java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());

      sql = "INSERT INTO personal_match VALUES (default, ?,?,?,?,?,?,?,?)";
      pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.setInt(1, userID);
      pStatement.setInt(2, gym_id);
      //pStatement.setDate(3, date.valueOf(game_date));
      pStatement.setTimestamp(3, sqlDate);
      pStatement.setTime(4, Time.valueOf(startAt));
      pStatement.setTime(5, Time.valueOf(endAt));
      pStatement.setString(6, game_type);
      pStatement.setInt(7, num_initial_players );
      pStatement.setInt(8, 0 );
      pStatement.executeUpdate();
      System.out.println("\nYou have successfully created a personal match!");
    }
    catch (Exception e){
      System.err.printf ("Cannot connect to server%n%s", e);
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    if (conn != null) {
      try {
        conn.close ();
        //System.out.println ("Disconnected from database.");
      }
      catch (Exception e) { /* ignore close errors */ }
    }
  }


  public void listAllPersonalMatches() {

    String sql = "select distinct * "
        + "from personal_match pm, personal_match_gymnasium pmg"
        + " where pm.gym_id = pmg.pmg_id";

    try {
      conn = getConnection();
      PreparedStatement pStatement = conn.prepareStatement(sql);
      ResultSet resultSet = pStatement.executeQuery();

      getPrompt("\n" + getLineBreak(41) + "\nList of All the Current Personal Matches:\n");

//      System.out.printf("%n%-11s%-12s%-8s%-13s%-13s%-11s%-12s%-18s%-19s%n",
//          "Match ID", "Created By", "Gym ID", "Date", "Start Time", "End Time", "Game Type",
//          "Initial Players", "Players Joined");

      while (resultSet.next()) {
        pmatch_id = resultSet.getInt("pmatch_id");
        player_id = resultSet.getInt("player_id");
        pmg_id = resultSet.getInt("pmg_id");
        pmg_name = resultSet.getString("pmg_name");
        pmg_location = resultSet.getString("pmg_location");
        game_date = resultSet.getDate("game_date");
        start_at = resultSet.getTime("start_at");
        end_at = resultSet.getTime("end_at");
        game_type = resultSet.getInt("game_type");
        num_initial_players = resultSet.getInt("num_initial_players");
        num_players_joined = resultSet.getInt("num_players_joined");

//        System.out.printf("%-11s%-12s%-8s%-13s%-13s%-11s%-12s%-18s%-19s%n",
//            pmatch_id, player_id, gym_id, game_date, start_at, end_at, game_type,
//            num_initial_players, num_players_joined);

        System.out.println();
        System.out.println("Match ID: " + pmatch_id);
        System.out.println("Match Creator ID: " + player_id);
        System.out.println("Gym ID: " + pmg_id);
        System.out.println("Gym Name: " + pmg_name);
        System.out.println("Gym Location: " + pmg_location);
        System.out.println("Date: " + game_date);
        System.out.println("Start Time: " + start_at);
        System.out.println("End Time: " + end_at);
        System.out.println("Game Type:" + game_type);
        System.out.println("Initial Players: " + num_initial_players);
        System.out.println("Players Joined: " + num_players_joined);
      }
      System.out.println();
    }
    catch (Exception e){
      System.err.printf ("Cannot connect to server%n%s", e);
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    if (conn != null) {
      try {
        conn.close ();
        //System.out.println ("Disconnected from database.");
      }
      catch (Exception e) { /* ignore close errors */ }
    }
  }

  /*
  Function: listFilteredPersonalMatches
  Editor: Xiongbo Hu
  Date: 20200327
   */
  public void listFilteredPersonalMatches(int loggedInUserID,
                                          String matchID,
                                          int gym_id,
                                          String date,
                                          String gameType,
                                          String playersLeft) throws ParseException {
    Boolean andFlag = false;
    Boolean foundFlag = false;
    String sql = "select * from personal_match";
    if (matchID.length() > 0) {
      sql += " where pmatch_id = ";
      sql += matchID;
      andFlag = true;
    }
//    if (location.length() > 0) {
//      if (andFlag == true) {
//        sql += " and location = \"";
//      } else {
//        sql += " where location = \"";
//      }
//      sql = sql + location + "\"";
//      andFlag = true;
//    }
    if (date.length() > 0) {
      try {
        //SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        //sdf.setTimeZone(TimeZone.getTimeZone("EST"));
        //java.util.Date gameDate = sdf.parse(date);
        //java.sql.Timestamp sqlDate = new java.sql.Timestamp(gameDate.getTime());
        if (andFlag == true) {
          sql += " and game_date > ";
        } else {
          sql += " where game_date > ";
        }
        //sql += gameDate.toString();
        sql += date;
        andFlag = true;
      } catch (Exception e) {
        System.out.printf("Date formation error.\n\n");
      }
    }
    if (gameType.length() > 0) {
      if (andFlag == true) {
        sql += " and game_type = ";
      } else {
        sql += " where game_type = ";
      }
      sql += gameType;
      andFlag = true;
    }
    if (playersLeft.length() > 0) {
      if (andFlag == true) {
        sql += " and num_initial_players * 2 - num_players_joined = ";
      } else {
        sql += " where num_initial_players * 2 - num_players_joined = ";
      }
      sql += playersLeft;
      andFlag = true;
    }
    System.out.printf("%s\n", sql);

    try {
      conn = getConnection();
      PreparedStatement pStatement = conn.prepareStatement(sql);
      ResultSet resultSet = pStatement.executeQuery();

      getPrompt("\n" + getLineBreak(36) + "\nFiltered Search of Persoanl Matches:\n");

      System.out.printf("%n%-12s%-14s%-8s%-13s%-13s%-11s%-12s%-18s%-19s%n",
              "Match ID", "Created by", "Gym ID", "Date", "Start Time", "End Time", "Game Type",
              "Initial Players", "Players Joined");
      while (resultSet.next()) {
        foundFlag = true;

        pmatch_id = resultSet.getInt("pmatch_id");
        player_id = resultSet.getInt("player_id");
        gym_id = resultSet.getInt("gym_id");
        game_date = resultSet.getDate("game_date");
        start_at = resultSet.getTime("start_at");
        end_at = resultSet.getTime("end_at");
        game_type = resultSet.getInt("game_type");
        num_initial_players = resultSet.getInt("num_initial_players");
        num_players_joined = resultSet.getInt("num_players_joined");

        System.out.printf("%-11s%-14s%-11s%-13s%-13s%-11s%-12s%-18s%-19s%n",
                pmatch_id, player_id, gym_id, game_date, start_at, end_at, game_type,
                num_initial_players, num_players_joined);
      }
      if (foundFlag == false) {
        System.out.printf("No Records\n\n");
      }
    } catch (Exception e) {
      System.err.printf("Cannot connect to server%n%s", e);

    }
  }

  public void listPersonalMatchesCreated(int loggedInUserID) {

    try {
      conn = getConnection();

//      String sql = "select pmatch_id, player_id, gym_id, game_date, start_at, end_at,"
//          + "game_type, num_initial_players, num_players_joined "
//          + "from player p, personal_match pm "
//          + "where p.pid=? "
//          + "and p.pid = pm.player_id";

      String sql = "select distinct pmatch_id, player_id, pmg.pmg_id, pmg.pmg_name, pmg.pmg_location,"
          + " game_date, start_at, end_at, game_type, num_initial_players, num_players_joined "
          + "from player p, personal_match pm, personal_match_gymnasium pmg "
          + "where p.pid=? "
          + "and p.pid = pm.player_id "
          + "and pm.gym_id = pmg.pmg_id";

      //System.out.println("logged in user: " + loggedInUserID);
      PreparedStatement pStatement = conn.prepareStatement(sql);
      pStatement.setInt(1, loggedInUserID);
      ResultSet resultSet = pStatement.executeQuery();

      getPrompt("\n" + getLineBreak(42) + "\nList of Personal Matches "
          + "you have Created:\n");

//      System.out.printf("%n%-11s%-12s%-8s%-13s%-13s%-11s%-12s%-18s%-19s%n",
//          "Match ID", "Created by", "Gym ID", "Date", "Start Time", "End Time", "Game Type",
//          "Initial Players", "Players Joined");

      while (resultSet.next()) {
        pmatch_id = resultSet.getInt("pmatch_id");
        player_id = resultSet.getInt("player_id");
        pmg_id = resultSet.getInt("pmg_id");
        pmg_name = resultSet.getString("pmg_name");
        pmg_location = resultSet.getString("pmg_location");
        game_date = resultSet.getDate("game_date");
        start_at = resultSet.getTime("start_at");
        end_at = resultSet.getTime("end_at");
        game_type = resultSet.getInt("game_type");
        num_initial_players = resultSet.getInt("num_initial_players");
        num_players_joined = resultSet.getInt("num_players_joined");

//        System.out.printf("%-11s%-12s%-8s%-13s%-13s%-11s%-12s%-18s%-19s%n",
//            pmatch_id, player_id, gym_id, game_date, start_at, end_at, game_type,
//            num_initial_players, num_players_joined);

        System.out.println();
        System.out.println("Match ID: " + pmatch_id);
        System.out.println("Match Creator ID: " + player_id);
        System.out.println("Gym ID: " + pmg_id);
        System.out.println("Gym Name: " + pmg_name);
        System.out.println("Gym Location: " + pmg_location);
        System.out.println("Date: " + game_date);
        System.out.println("Start Time: " + start_at);
        System.out.println("End Time: " + end_at);
        System.out.println("Game Type:" + game_type);
        System.out.println("Initial Players: " + num_initial_players);
        System.out.println("Players Joined: " + num_players_joined);
       }
      System.out.println();

    } catch (Exception e) {
      System.err.printf("Cannot connect to server%n%s", e);
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    if (conn != null) {
      try {
        conn.close();
        //System.out.println("Disconnected from database.");
      } catch (Exception e) { /* ignore close errors */ }
    }
  }

  public void listPersonalMatchesJoined(int loggedInUserID) {

    try {
      conn = getConnection();

//      String sql = "select pmatch_id, player_id, pmg.pmg_id, pmg.pmg_name, pmg.pmg_location, game_date, "
//          + "start_at, end_at, game_type, num_initial_players, num_players_joined "
//              + "from player p, personal_match pm, personal_match_gymnasium pmg "
//              + "where p.pid=? "
//              + "and p.pid = pm.player_id "
//              + "and pm.gym_id = pmg.pmg_id";

      String sql = "select * "
          + "from player p, personal_match pm, personal_match_players pmp, "
          + "personal_match_gymnasium pmg "
          + "where p.pid=? "
          + "and p.pid= pmp.p_id "
          + "and pmp.match_id = pm.pmatch_id "
          + "and pm.gym_id = pmg.pmg_id";

      PreparedStatement pStatement = conn.prepareStatement(sql);
      pStatement.setInt(1, loggedInUserID);
      ResultSet resultSet = pStatement.executeQuery();

      getPrompt("\n" + getLineBreak(41) + "\nList of Personal Matches "
              + "you have Joined:\n");

//      System.out.printf("%n%-11s%-12s%-8s%-13s%-13s%-11s%-12s%-18s%-19s%n",
//              "Match ID", "Created ID", "Gym ID", "Date", "Start Time", "End Time", "Game Type",
//              "Initial Players", "Players Joined");

      while (resultSet.next()) {
        pmatch_id = resultSet.getInt("pmatch_id");
        player_id = resultSet.getInt("player_id");
        pmg_id = resultSet.getInt("pmg_id");
        pmg_name = resultSet.getString("pmg_name");
        pmg_location = resultSet.getString("pmg_location");
        game_date = resultSet.getDate("game_date");
        start_at = resultSet.getTime("start_at");
        end_at = resultSet.getTime("end_at");
        game_type = resultSet.getInt("game_type");
        num_initial_players = resultSet.getInt("num_initial_players");
        num_players_joined = resultSet.getInt("num_players_joined");

//        System.out.printf("%-11s%-12s%-8s%-13s%-13s%-11s%-12s%-18s%-19s%n",
//                pmatch_id, player_id, gym_id, game_date, start_at, end_at, game_type,
//                num_initial_players, num_players_joined);


        System.out.println();
        System.out.println("Match ID: " + pmatch_id);
        System.out.println("Match Creator ID: " + player_id);
        System.out.println("Gym ID: " + pmg_id);
        System.out.println("Gym Name: " + pmg_name);
        System.out.println("Gym Location: " + pmg_location);
        System.out.println("Date: " + game_date);
        System.out.println("Start Time: " + start_at);
        System.out.println("End Time: " + end_at);
        System.out.println("Game Type:" + game_type);
        System.out.println("Initial Number of Players: " + num_initial_players);
        System.out.println("Players Joined: " + num_players_joined);

      }
      System.out.println();

    } catch (Exception e) {
      System.err.printf("Cannot connect to server%n%s", e);
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    if (conn != null) {
      try {
        conn.close();
        //System.out.println("Disconnected from database.");
      } catch (Exception e) { /* ignore close errors */ }
    }
  }

  public void joinPersonalMatch(int p_id) {
    Scanner sc = new Scanner(System.in);

    System.out.println("\n\n" + "=====================================================" +
        "\nEnter the Match ID of the Game you would like to Join:\n"
        + "(See the list of current personal matches above)");

    System.out.printf("%n> ");
    int match_id = Integer.parseInt(sc.next());

    try {
      conn = getConnection();
      sql = "INSERT INTO personal_match_players VALUES (?,?)";
      pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.setInt(1, p_id);
      pStatement.setInt(2, match_id );
      pStatement.executeUpdate();
      System.out.println("\nYou have successfully joined a personal match!");

      sql = "UPDATE personal_match SET num_players_joined = num_players_joined + 1 "
          + "where pmatch_id= ?";
      pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.setInt(1, match_id);
      pStatement.executeUpdate();

    }
    catch (Exception e){
      System.err.printf ("Cannot connect to server%n%s", e);
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    if (conn != null) {
      try {
        conn.close ();
        //System.out.println ("Disconnected from database.");
      }
      catch (Exception e) { /* ignore close errors */ }
    }


  }


  public void searchPersonalMatchesByLocation() {}

  /**
   * Concatenates set number of characters for line break.
   *
   * @param num the number of characters in the line break
   * @return a line break with a set number of characters
   */
  protected String getLineBreak(int num) {
    String lineBreak = "";
    for (int i = 0; i < num; i++) {
      lineBreak += "=";
    }
    return lineBreak;
  }

  /**
   * User prompt.
   *
   * @param str string to prompt user
   */
  protected void getPrompt(String str) {
    System.out.print(str);
  }


}



