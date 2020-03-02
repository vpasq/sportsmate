package sportsmate.init;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import sportsmate.dao.DAO;

public class DropTables extends DAO {
  private Connection conn;
  private String tablesDropped;

  public DropTables() {
    String sql;
    PreparedStatement pStatement;

    try {
      conn = getConnection();

//----

      sql = "drop table IF EXISTS player";

      pStatement = conn
          .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.execute();
      tablesDropped = "player:";
      System.out.println("Table dropped successfully...");
//----


//----

      sql = "drop table IF EXISTS user";

      pStatement = conn
          .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.execute();
      tablesDropped += "user:";
      System.out.println("Table dropped successfully...");
//----

    }
    catch (Exception e) {
      System.err.printf("Cannot connect to server%n%s", e);
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    if (conn != null) {
      try {
        conn.close();
        System.out.println("Disconnected from database.");
      } catch (Exception e) { /* ignore close errors */ }
    }
  }

  public String getTablesDropped() {
    return tablesDropped;
  }

}
