package sportsmate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDAO extends DAO {
  private Connection conn;
  private String username;
  private String password;

  public boolean authLogin(String username, String password) {
    this.username = username;
    this.password = password;

    try {
      conn = getConnection();
      String sql = "select * from user where username=? and password=?";
      PreparedStatement pStatement = getConnection().prepareStatement(sql);
      pStatement.setString(1, this.username);
      pStatement.setString(2, this.password);
      ResultSet resultSet = pStatement.executeQuery();
      return resultSet.next();
    }
    catch (Exception e){
      System.err.printf ("Cannot connect to server%n%s", e);
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    if (conn != null) {
      try {
        conn.close ();
        System.out.println ("Disconnected from database.");
      }
      catch (Exception e) { /* ignore close errors */ }
    }

    return false;
  }

}
