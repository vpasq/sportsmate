package sportsmate.dao;

import static java.sql.Types.NULL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TeamMatchDAO extends DAO {
  private Connection conn;
  private String sql;
  private PreparedStatement pStatement;
  private int match_id;
  private int host_id;
  private int guest_id;
  private int gym_id;
  private Date game_date;
  private Time start_at;
  private Time end_at;
  private int admin_id;
  private String fname;
  private String lname;
  private String username;


  public void createTeamMatch(int loggedInUserID, int hostTeamID, int gym_id,
      String game_date, String startAt, String endAt ) {


        try {
          conn = getConnection();

          String sql = "select t.team_id "
              + "from player p, team t "
              + "where p.user_id=? "
              + "and p.pid = t.admin_id "
              + "and t.team_id=? ";

          PreparedStatement pStatement = conn.prepareStatement(sql);
          pStatement.setInt(1, loggedInUserID);
          pStatement.setInt(2, hostTeamID);
          ResultSet resultSet = pStatement.executeQuery();

         if (resultSet.next()) {

          SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
          sdf.setTimeZone(TimeZone.getTimeZone("EST"));
          //java.sql.Date sqlDate = new java.sql.Date(date.getTime());
          java.util.Date date = sdf.parse(game_date);
          java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());

          String sql2 = "INSERT INTO team_match VALUES (default,?,?,?,?,?,?)";
          PreparedStatement pStatement2 = conn
              .prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
          pStatement2.setInt(1, hostTeamID);
          pStatement2.setNull(2, NULL);
          pStatement2.setInt(3, gym_id);
          pStatement2.setTimestamp(4, sqlDate);
          pStatement2.setTime(5, Time.valueOf(startAt));
          pStatement2.setTime(6, Time.valueOf(endAt));
          pStatement2.executeUpdate();
          System.out.println("\nYOU HAVE SUCCESSFULLY CREATED A TEAM MATCH!");

       } else {
        System.out.println("\nYou cannot create this Team Match because you are not the"
            + " admin of the Team.");
      }

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


  public boolean isAdmin(int loggedInUserID2, int hostTeamID2) {

    try {
      conn = getConnection();

      String sql2 = "select t.team_id "
          + "from player p, team t "
          + "where p.user_id=? "
          + "and p.pid = t.admin_id "
          + "and t.team_id=? ";

      PreparedStatement pStatement2 = conn.prepareStatement(sql2);
      pStatement2.setInt(1, loggedInUserID2);
      pStatement2.setInt(2, hostTeamID2);
      ResultSet resultSet2 = pStatement2.executeQuery();

      if (resultSet2.next()) {
        return true;

      }


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
    return false;
  }






  public void joinTeamMatch(int loggedInUserID, int guestTeamID, int teamMatchID) {

    try {
      conn = getConnection();

      String sql = "select t.team_id "
          + "from player p, team t, team_match tm "
          + "where t.admin_id=? "
          + "and t.team_id=? ";

      PreparedStatement pStatement = conn.prepareStatement(sql);
      pStatement.setInt(1, loggedInUserID);
      pStatement.setInt(2, guestTeamID);
      ResultSet resultSet = pStatement.executeQuery();

      if (resultSet.next()) {

        String sql2 = "update team_match "
            + "set guest_id = ? "
            + "where match_id =?";

      PreparedStatement pStatement2 = conn.prepareStatement(sql2);
      pStatement2.setInt(1, guestTeamID);
      pStatement2.setInt(2, teamMatchID);
      int numRowsUpdated = pStatement2.executeUpdate();;

        System.out.println("\nYOU HAVE SUCCSEEFULLY JOINED A TEAM MATCH!");
      } else {
        System.out.println("\nYOU CANNOT JOIN THIS TEAM MATCH BECAUSE YOU ARE NOT THE"
            + " ADMIN OF YOUR TEAM, OR THE MATCH DOES NOT EXIST!");
      }

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


  public void listTeamMatchesCreated(int loggedInUserID) {

    try {
      conn = getConnection();


//      String sql = "select match_id, host_id, guest_id, gym_id, game_date, start_at, end_at "
//          + "from team t, team_match tm "
//          + "where t.admin_id =? "
//          + "and t.team_id = tm.host_id";

      String sql = "select  * "
          + "from user u, player p, team t, team_match tm "
          + "where p.pid=? "
          + "and p.user_id = u.id "
          + "and p.pid = t.admin_id "
          + "and t.team_id = tm.host_id ";


      //System.out.println("logged in user: " + loggedInUserID);
      PreparedStatement pStatement = conn.prepareStatement(sql);
      pStatement.setInt(1, loggedInUserID);
      ResultSet resultSet = pStatement.executeQuery();

      getPrompt("\n" + getLineBreak(38) + "\nList of Team Matches "
          + "you have created:\n");

//      System.out.printf("%n%-10s%-14s%-15s%-10s%-11s%-12s%-10s%n",
//          "Match ID", "Host Team ID", "Guest Team ID", "Location", "Game Date",
//          "Start Time", "End Time");

      while (resultSet.next()) {
        match_id = resultSet.getInt("match_id");
        host_id = resultSet.getInt("host_id");
        guest_id = resultSet.getInt("guest_id");
        gym_id = resultSet.getInt("gym_id");
        game_date = resultSet.getDate("game_date");
        start_at = resultSet.getTime("start_at");
        end_at = resultSet.getTime("end_at");
        fname = resultSet.getString("u.fname");
        lname = resultSet.getString("u.lname");
        username = resultSet.getString("u.username");

        System.out.println();
        System.out.println("Match Creator: " + username);
        System.out.println("Match ID: " + match_id);
        System.out.println("Host Team ID: " + host_id);
        System.out.println("Gym ID: " + gym_id);
        System.out.println("Date: " + game_date);
        System.out.println("Start Time: " + start_at);
        System.out.println("End Time: " + end_at);



//        System.out.printf("%-10s%-14s%-15s%-10s%-11s%-12s%-10s%n",
//            match_id, host_id, guest_id, location, game_date,
//            start_at, end_at);
      }

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

  public void listTeamMatchesMyTeamJoined(int loggedInUserID) {

    try {
      conn = getConnection();

//      String sql = "select distinct tm.match_id, tm.host_id, tm.guest_id, tm.location, "
//          + "tm.game_date, tm.start_at, tm.end_at "
//          + "from player p, team t, team_match tm "
//          + "where t.admin_id=? "
//          + "and t.team_id = tm.guest_id ";

//      String sql = "select  u.id, p.pid, u.fname, u.lname, u.username, t.admin_id, "
//          + "tm.match_id, tm.host_id, tm.guest_id, tm.location, "
//          + "tm.game_date, tm.start_at, tm.end_at "
//          + "from user u, player p, team t, team_match tm "
//          + "where p.pid=? "
//          + "and p.user_id = u.id "
//          + "and p.pid = t.admin_id "
//          + "and t.team_id = tm.guest_id ";

      String sql = "select  * "
          + "from user u, player p, team t, team_match tm "
          + "where p.pid=? "
          + "and p.user_id = u.id "
          + "and p.pid = t.admin_id "
          + "and t.team_id = tm.guest_id ";


      System.out.println("Logged in User = " + loggedInUserID);

      //System.out.println("logged in user: " + loggedInUserID);
      PreparedStatement pStatement = conn.prepareStatement(sql);
      pStatement.setInt(1, loggedInUserID);
      ResultSet resultSet = pStatement.executeQuery();

      getPrompt("\n" + getLineBreak(41) + "\nList of Team Matches "
          + "my team(s) have joined:\n");

//      System.out.printf("%n%-10s%-14s%-15s%-10s%-11s%-12s%-10s%n",
//          "Match ID", "Host Team ID", "Guest Team ID", "Location", "Game Date",
//          "Start Time", "End Time");

      while (resultSet.next()) {

        admin_id = resultSet.getInt("t.admin_id");
        match_id = resultSet.getInt("tm.match_id");
        host_id = resultSet.getInt("tm.host_id");
        guest_id = resultSet.getInt("tm.guest_id");
        gym_id = resultSet.getInt("tm.gym_id");
        game_date = resultSet.getDate("tm.game_date");
        start_at = resultSet.getTime("tm.start_at");
        end_at = resultSet.getTime("tm.end_at");
        fname = resultSet.getString("u.fname");
        lname = resultSet.getString("u.lname");
        username = resultSet.getString("u.username");

        System.out.println();
        System.out.println("Match ID: " + match_id);
       System.out.println("Match Creator: " + username);
        System.out.println("Host Team ID: " + host_id);
        System.out.println("Guest Team ID: " + guest_id);
        System.out.println("Gym ID: " + gym_id);
        System.out.println("Date: " + game_date);
        System.out.println("Start Time: " + start_at);
        System.out.println("End Time: " + end_at);



//        System.out.printf("%n%-10s%-14s%-15s%-10s%-11s%-12s%-10s%n",
//            match_id, host_id, guest_id, location, game_date,
//            start_at, end_at);
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


  public void listAllTeamMatches() {
    int team_id;
    String team_name;

    try {
      conn = getConnection();

//      String sql = "select distinct t.admin_id, tm.match_id, tm.host_id, tm.guest_id,tm.location,"
//          + "tm.game_date, tm.game_date, tm.start_at, tm.end_at "
//          + "from team t, team_match tm, player p "
//          + "where t.team_id = tm.host_id or t.team_id = tm.guest_id";

      String sql = "select distinct u.fname, u.lname, u.username, t.admin_id, "
          + "tm.match_id, tm.host_id, tm.guest_id, tm.gym_id,"
          + "tm.game_date, tm.game_date, tm.start_at, tm.end_at "
          + "from team t, team_match tm, player p, user u "
          + "where p.pid = t.admin_id "
          + "and u.id = p.user_id "
          + "and t.team_id = tm.host_id";


      //System.out.println("logged in user: " + loggedInUserID);
      PreparedStatement pStatement = conn.prepareStatement(sql);
      ResultSet resultSet = pStatement.executeQuery();

      getPrompt("\n" + getLineBreak(29) + "\nList of All the Team Matches:\n");

//      System.out.printf("%n%-10s%-14s%-15s%-10s%-11s%-12s%-10s%n",
//          "Match ID", "Host Team ID", "Guest Team ID", "Location", "Game Date",
//          "Start Time", "End Time");

      while (resultSet.next()) {
        admin_id = resultSet.getInt("t.admin_id");
        match_id = resultSet.getInt("tm.match_id");
        host_id = resultSet.getInt("tm.host_id");
        guest_id = resultSet.getInt("tm.guest_id");
        gym_id = resultSet.getInt("tm.gym_id");
        game_date = resultSet.getDate("tm.game_date");
        start_at = resultSet.getTime("tm.start_at");
        end_at = resultSet.getTime("tm.end_at");
        fname = resultSet.getString("u.fname");
        lname = resultSet.getString("u.lname");
        username = resultSet.getString("u.username");

        System.out.println();
        System.out.println("Match Creator: " + username);
        System.out.println("Match ID: " + match_id);
        System.out.println("Host Team ID: " + host_id);
//        System.out.println("Match Creator Last Name: " + lname);
//        System.out.println("Match Creator Username: " + username);
        System.out.println("Guest Team ID: " + guest_id);
        System.out.println("Gym ID: " + gym_id);
        System.out.println("Date: " + game_date);
        System.out.println("Start Time: " + start_at);
        System.out.println("End Time: " + end_at);


//        System.out.printf("%n%-10s%-14s%-15s%-10s%-11s%-12s%-10s",
//            match_id, host_id, guest_id, location, game_date,
//            start_at, end_at);

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
