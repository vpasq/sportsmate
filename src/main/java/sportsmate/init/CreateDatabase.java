package sportsmate.init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import sportsmate.dao.DAO;

public class CreateDatabase extends DAO {
  private Connection conn;
  private String url = "jdbc:mysql://localhost";
  private String username = "vpasq";
  private String password = "test";
  private String databaseCreated;

  public CreateDatabase() {

    try {
      conn = DriverManager.getConnection(url, username, password);
      String sql = "create database if not exists sportsmate";
      Statement stmt = conn.createStatement();
      int d = stmt.executeUpdate(sql);
      databaseCreated = "database created";
      //System.out.println("Database created successfully...");
    }
    catch (Exception e){
      System.err.printf ("Cannot connect to server%n%s", e);
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }

  public String getDatabaseCreated() {
    return databaseCreated;
  }

}
