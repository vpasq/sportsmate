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

public class TeamMatchDAO extends DAO {
  private Connection conn;
  private String sql;
  private PreparedStatement pStatement;

  public void createTeam(String team_name, int admin_id) {
    try {
      conn = getConnection();

      sql = "INSERT INTO team VALUES (default, ?,?)";
      pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.setString(1, team_name);
      pStatement.setInt(2, admin_id);
      pStatement.executeUpdate();
      System.out.println("\nYou have successfully created a team!");

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

  public void joinTeam(String teamName, int playerID) {
    try {
      conn = getConnection();

      sql = "INSERT INTO team_match_players VALUES (?,?)";
      pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.setInt(1, playerID);
      pStatement.setString(2, teamName);
      pStatement.executeUpdate();
      System.out.printf("\nYou have successfully joined a team!");



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
    System.out.println("lll" + t_id);
    System.out.println("bbb" + tmplayer_id);
    try {
      conn = getConnection();

      sql = "DELETE from team_match_players where tmplayer_id=? and t_id=?";
      PreparedStatement pStatement = conn.prepareStatement(sql);
      pStatement.setInt(1, tmplayer_id);
      pStatement.setInt(2, t_id);
      pStatement.executeUpdate();
      System.out.printf("\nYou have successfully left the team!");

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
    int team_id;
    String team_name;

    try {
      conn = getConnection();

      String sql = "select team_id, team_name "
          + "from player p, team t "
          + "where p.user_id=? "
          + "and p.pid = t.admin_id ";

      //System.out.println("logged in user: " + loggedInUserID);
      PreparedStatement pStatement = conn.prepareStatement(sql);
      pStatement.setInt(1, loggedInUserID);
      ResultSet resultSet = pStatement.executeQuery();

      getPrompt("\n" + getLineBreak(47) + "\nBelow is a of list Teams"
          + " that you have created:\n");
      System.out.printf("%n%-11s%-14s%n",
          "Team ID", "Team Name");

      while (resultSet.next()) {
        team_id = resultSet.getInt("team_id");
        team_name = resultSet.getString("team_name");

        System.out.printf("%-11s%-14s%n", team_id, team_name);
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


  public void listTeamsJoined(int loggedInUserID) {
    int team_id;
    String team_name;

    try {
      conn = getConnection();

      String sql = "select t.team_id, t.team_name "
          + "from player p, team t, team_match_players tmp "
          + "where p.user_id=? "
          + "and p.pid = tmp.tmplayer_id "
          + "and t.team_id = tmp.t_id";

      //System.out.println("logged in user: " + loggedInUserID);
      PreparedStatement pStatement = conn.prepareStatement(sql);
      pStatement.setInt(1, loggedInUserID);
      ResultSet resultSet = pStatement.executeQuery();

      getPrompt("\n" + getLineBreak(47) + "\nBelow is a of list Teams"
          + " that you have joined:\n");
      System.out.printf("%n%-11s%-14s%n",
          "Team ID", "Team Name");

      while (resultSet.next()) {
        team_id = resultSet.getInt("team_id");
        team_name = resultSet.getString("team_name");

        System.out.printf("%-11s%-14s%n", team_id, team_name);
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


  public void listAllTeams() {
    int team_id;
    String team_name;

    try {
      conn = getConnection();

      String sql = "select * from team ";

      //System.out.println("logged in user: " + loggedInUserID);
      PreparedStatement pStatement = conn.prepareStatement(sql);
      ResultSet resultSet = pStatement.executeQuery();

      getPrompt("\n" + getLineBreak(47) + "\nBelow is a of list All Teams:\n");
      System.out.printf("%n%-11s%-14s%n", "Team ID", "Team Name");

      while (resultSet.next()) {
        team_id = resultSet.getInt("team_id");
        team_name = resultSet.getString("team_name");

        System.out.printf("%-11s%-14s%n", team_id, team_name);
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
