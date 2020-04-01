package sportsmate.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TeamMatchDAO extends DAO {
  private Connection conn;

  public void createTeam(String name) {

    try {
      conn = getConnection();


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
  }


}
