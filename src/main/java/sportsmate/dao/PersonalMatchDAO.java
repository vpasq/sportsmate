package sportsmate.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class PersonalMatchDAO extends DAO {
  private Connection conn;

  public void createPersonalMatch(int userID, String location, String game_date, String startAt,
      String endAt, String game_type, String num_current_players) {

    String sql;
    PreparedStatement pStatement;

    try {
      conn = getConnection();
      sql = "INSERT INTO personal_match VALUES (default, ?,?,?,?,?,?,?)";
      pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.setInt(1, userID);
      pStatement.setString(2, location);
      pStatement.setString(3, game_date);
      pStatement.setString(4, startAt);
      pStatement.setString(5, endAt);
      pStatement.setString(6, game_type);
      pStatement.setString(7, num_current_players);
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

}



