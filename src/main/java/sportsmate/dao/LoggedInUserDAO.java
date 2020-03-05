package sportsmate.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class LoggedInUserDAO extends DAO {
  private Connection conn;
  private int userid;
  private String fname;
  private String lname;
  private String gender;
  private String username;

  public LoggedInUserDAO(int userID) {

    try {
      conn = getConnection();

      String sql = "select id, fname, lname, gender, username"
          + " from user where username=?";
      PreparedStatement pStatement = conn.prepareStatement(sql);
      pStatement.setInt(1, userID);
      ResultSet resultSet = pStatement.executeQuery();

      while (resultSet.next()) {
        userid = resultSet.getInt("id");
        fname = resultSet.getString("fname");
        lname = resultSet.getString("lname");
        gender = resultSet.getString("gender");
        username = resultSet.getString("username");
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

  public int getUserid() {
    return userid;
  }

  public String getFname() {
    return fname;
  }

  public String getLname() {
    return lname;
  }

  public String getGender() {
    return gender;
  }

  public String getUsername() {
    return username;
  }
}
