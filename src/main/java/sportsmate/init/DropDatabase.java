package sportsmate.init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import sportsmate.dao.DAO;

public class DropDatabase {
  private Connection conn;
  private String databaseDropped;

  public DropDatabase() {
    String url = "jdbc:mysql://localhost";
    String username = "vpasq";
    String password = "test";

    try {
      Connection conn = DriverManager.getConnection(url, username, password);

      String sql = "drop database if exists sportsmate";
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(sql);
      databaseDropped = "database dropped";
      System.out.println("Database dropped successfully...");
    }
    catch (Exception e){
      System.err.printf ("Cannot connect to server%n%s", e);
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }

  public String getDatabaseDropped() {
    return databaseDropped;
  }

}
