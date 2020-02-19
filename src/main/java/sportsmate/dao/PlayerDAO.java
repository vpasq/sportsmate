package sportsmate.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class PlayerDAO extends DAO {
  private Connection conn;
  private String fname;
  private String lname;
  private String password;

  public void createPlayer(String fname, String lname, String username, String pswd) {
    this.fname = fname;
    this.lname = lname;
    this.password = password;

    try {
      conn = getConnection();
      String sql = "INSERT INTO user VALUES (default, ?,?,?,?)";
      PreparedStatement pStatement = getConnection()
          .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.setString(1, fname);
      pStatement.setString(2, lname);
      pStatement.setString(3, username);
      pStatement.setString(4, pswd);
      pStatement.execute();
      System.out.printf("Inserted Row into %s%n", "user table");
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
  }
}
