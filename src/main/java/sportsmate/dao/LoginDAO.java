package sportsmate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDAO extends DAO {
  private Connection conn;
  private int userID;
  boolean userExists;

  public boolean authLogin(String username, String password) {

    try {
      conn = getConnection();
      String sql = "select * from user where username=? and password=?";
//      String sql = "select id, fname, lname, gender, username, password"
//          + " from user where username=? and password=?";
      PreparedStatement pStatement = conn.prepareStatement(sql);
      pStatement.setString(1, username);
      pStatement.setString(2, password);
      ResultSet resultSet = pStatement.executeQuery();

      while (resultSet.next()) {
        this.userID = resultSet.getInt("id");
        userExists = true;
      }

      return userExists;
      //return resultSet.next();
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

    return userExists;
  }

  public int getLoggedInUserID() {
    return userID;
  }

}
