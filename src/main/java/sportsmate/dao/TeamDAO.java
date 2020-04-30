package sportsmate.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TeamDAO extends DAO {
  private Connection conn;
  private String sql;
  private PreparedStatement pStatement;
  int team_id;
  String team_name;
  int admin_id;
  String fname;
  String lname;
  String username;

  public void createTeam(String team_name, int admin_id) {
    try {
      conn = getConnection();

      sql = "INSERT INTO team VALUES (default, ?,?)";
      pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.setString(1, team_name);
      pStatement.setInt(2, admin_id);
      pStatement.executeUpdate();
      //System.out.println("\nYou have successfully created a team!");
      System.out.println("\nYOU HAVE SUCCSESSFULLY CREATED A TEAM!");

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

  public void joinTeam(int playerID) {

    Scanner sc = new Scanner(System.in);

    System.out.print("\n\n" + "=====================================================" +
        "\nEnter the Team ID of the Team you would like to Join:\n"
        + "(See list above): ");
    int teamID = Integer.parseInt(sc.next());

    try {
      conn = getConnection();

      sql = "INSERT INTO team_match_players VALUES (?,?)";
      pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.setInt(1, playerID);
      pStatement.setInt(2, teamID);
      pStatement.executeUpdate();
      System.out.printf("\nYOU HAVE SUCCESSFULLY JOINED THE TEAM!\n");



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


  public void leaveTeam(int tmplayer_id, int t_id) {

    try {
      conn = getConnection();

      sql = "DELETE from team_match_players where tmplayer_id=? and t_id=?";
      PreparedStatement pStatement = conn.prepareStatement(sql);
      pStatement.setInt(1, tmplayer_id);
      pStatement.setInt(2, t_id);
      pStatement.executeUpdate();
      System.out.printf("\nYOU HAVE SUCCESSFULLY LEFT THE TEAM!\n");

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


  public void listTeamsCreated(int loggedInUserID) {

    try {
      conn = getConnection();

//      String sql = "select team_id, team_name, admin_id "
//          + "from player p, team t "
//          + "where p.pid=? "
//          + "and p.pid = t.admin_id";

      String sql = "select u.username, t.team_id, t.team_name, t.admin_id "
          + "from user u, player p, team t "
          + "where p.pid=? "
          + "and p.user_id = u.id "
          + "and p.pid= t.admin_id";

      //System.out.println("logged in user: " + loggedInUserID);
      PreparedStatement pStatement = conn.prepareStatement(sql);
      pStatement.setInt(1, loggedInUserID);
      ResultSet resultSet = pStatement.executeQuery();

      getPrompt("\n" + getLineBreak(38) + "\nList of Teams "
          + "where you are the admin:\n");

//      System.out.printf("%n%-11s%-25s%-10s%n",
//          "Team ID", "Team Name", "Admin ID");

      while (resultSet.next()) {
        team_id = resultSet.getInt("team_id");
        team_name = resultSet.getString("team_name");
        admin_id = resultSet.getInt("admin_id");
//        fname = resultSet.getString("u.fname");
//        lname = resultSet.getString("u.lname");
        username = resultSet.getString("u.username");

        System.out.println();
        System.out.println("Team Creator: " + username);
        System.out.println("Team ID: " + team_id);
        System.out.println("Team Name: " + team_name);
        System.out.println("Admin ID: " + admin_id);

//        System.out.printf("%-11s%-25s%-10s%n", team_id, team_name, admin_id);
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


  public void listTeamsJoined(int loggedInUserID) {

    try {
      conn = getConnection();

//      String sql = "select * "
//          + "from player p, team t, team_match_players tmp "
//          + "where p.user_id=? "
//          + "and p.pid = tmp.tmplayer_id "
//          + "and t.team_id = tmp.t_id";

      String sql = "select * "
          + "from user u, player p, team t, team_match_players tmp "
          + "where p.pid=? "
          + "and p.user_id = u.id "
          + "and p.pid = tmp.tmplayer_id ";

      //System.out.println("logged in user: " + loggedInUserID);
      PreparedStatement pStatement = conn.prepareStatement(sql);
      pStatement.setInt(1, loggedInUserID);
      ResultSet resultSet = pStatement.executeQuery();

      getPrompt("\n" + getLineBreak(30) + "\nList of Teams"
          + " you Have Joined:\n");

//      System.out.printf("%n%-11s%-14s%n",
//          "Team ID", "Team Name");

      while (resultSet.next()) {
        team_id = resultSet.getInt("team_id");
        team_name = resultSet.getString("team_name");
        admin_id = resultSet.getInt("admin_id");
        fname = resultSet.getString("u.fname");
        lname = resultSet.getString("u.lname");
        username = resultSet.getString("u.username");

        System.out.println();
        System.out.println("Team Creator: " + username);
        System.out.println("Team ID: " + team_id);
        System.out.println("Team Name: " + team_name);
        System.out.println("Admin ID: " + admin_id);


//        System.out.printf("%-11s%-14s%n", team_id, team_name);
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


  public void listAllTeams() {

    try {
      conn = getConnection();

      //String sql = "select * from team ";

      String sql = "select * "
          + "from team t, player p, user u "
          + "where t.admin_id = p.pid and p.user_id = u.id";

      //System.out.println("logged in user: " + loggedInUserID);
      PreparedStatement pStatement = conn.prepareStatement(sql);
      ResultSet resultSet = pStatement.executeQuery();

      getPrompt("\n" + getLineBreak(22) + "\nList of All the Teams:\n");


      while (resultSet.next()) {
        team_id = resultSet.getInt("team_id");
        team_name = resultSet.getString("team_name");
        admin_id = resultSet.getInt("admin_id");
        fname = resultSet.getString("fname");
        lname = resultSet.getString("lname");
        username = resultSet.getString("username");

        System.out.println();
        System.out.println("Team Creator: " + username);
        System.out.println("Team ID: " + team_id);
        System.out.println("Team Name: " + team_name);
        System.out.println("Admin ID: " + admin_id);

//        System.out.printf("%-11s%-14s%n", team_id, team_name);
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


  public void cancelTeamMatch(int loggedInUserID) {

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
