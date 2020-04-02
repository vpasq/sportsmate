package sportsmate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

  public Connection getConnection() {
    String url = "jdbc:mysql://localhost:3306/sportsmate?serverTimezone=UTC#";
    String username = "demo";
    String password = "demo";

    try  {
        return DriverManager.getConnection(url, username, password);
      }
    catch (SQLException e) {
        System.err.println(e.getMessage());
        e.printStackTrace();
    }
    catch (Exception e) {
      System.err.printf ("Cannot connect to server%n%s", e);
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    return null;
  }

}
