package sportsmate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.junit.*;
import static org.junit.Assert.*;

public class TestConnection {
  private String url = "jdbc:mysql://localhost/sportsmate";
  private String userName = "demo";
  private String password = "demo";
  private Connection conn;

  @Test
  public void testSuccessfulDBConnection() {
    // setup
    String expected = "connected";
    String actual;

    // exercise
    try {
      conn = DriverManager.getConnection(url, userName, password);
      actual = "connected";
    } catch (Exception e) {
      actual = "not connected";
    }

    // verify
    assertEquals(expected, actual);

    // teardown
    conn = null;
  }

  @Test
  public void testSuccessfulDBDisconnection() {
    // setup
    String expected = "disconnected";
    String actual;

    // exercise
    try {
      conn = DriverManager.getConnection (url, userName, password);
      actual = "connected";
    }
    catch (Exception e) {
      actual = "not connected";
    }
    if (conn != null) {
      try {
        conn.close();
        actual = "disconnected";
      }
      catch (Exception e) { /* ignore close errors */ }
    }

    // verify
    assertEquals(expected, actual);

    // teardown
    conn = null;
  }

  @Test
  public void testSuccessDAOconnection() {

    DAO dao = new DAO();


  }


}
