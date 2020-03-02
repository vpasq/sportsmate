package sportsmate.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class PlayerDAO extends DAO {
  private String sql;
  private Connection conn;
  private PreparedStatement pStatement;
  private String fname;
  private String lname;
  private String gender;
  private String password;

  public void createPlayer(String fname, String lname, String gender, String username, String pswd) {
    this.fname = fname;
    this.lname = lname;
    this.gender = gender;
    this.password = pswd;

    try {
      conn = getConnection();
      sql = "INSERT INTO user VALUES (default, ?,?,?,?,?)";
      pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.setString(1, fname);
      pStatement.setString(2, lname);
      pStatement.setString(3, gender);
      pStatement.setString(4, username);
      pStatement.setString(5, pswd);
      pStatement.executeUpdate();
      System.out.printf("Inserted Row into %s%n", "user table");

      ResultSet generatedKeys = pStatement.getGeneratedKeys();
      generatedKeys.next();

      sql = "insert into player VALUES (default, ?)";
      pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.setString(1, Integer.toString(generatedKeys.getInt(1)));
      pStatement.execute();
      System.out.printf("Inserted Row into %s%n", "player table");

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
