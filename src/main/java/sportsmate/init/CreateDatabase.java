package sportsmate.init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import sportsmate.dao.DAO;

public class CreateDatabase extends DAO {
  private String databaseCreated;

  public CreateDatabase() {
    Connection conn;
    String url = "jdbc:mysql://localhost";
    String username = "vpasq";
    String password = "test";

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
