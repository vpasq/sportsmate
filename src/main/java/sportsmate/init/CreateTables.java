package sportsmate.init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import sportsmate.dao.DAO;

public class CreateTables extends DAO {
  private Connection conn;
  private String tablesCreated;

  public CreateTables() {
    String sql;
    PreparedStatement pStatement;

    try {
      conn = getConnection();

      sql = "CREATE TABLE IF NOT EXISTS user"
          + "("
          + " id integer primary key auto_increment,"
          + " fname varchar(25) NOT NULL,"
          + " lname varchar(25) NOT NULL,"
          + " gender varchar(10) NOT NULL,"
          + " username varchar(25) NOT NULL,"
          + " password varchar(25) NOT NULL"
          + ")";

      pStatement = conn
          .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.execute();
      tablesCreated = "user:";
      System.out.println("table created successfully...");
//----

      sql = "CREATE TABLE IF NOT EXISTS player"
          + "("
          + " pid integer primary key auto_increment,"
          + " user_id integer NOT NULL,"
          + " foreign key (user_id) references user(id)"
          + ")";

      pStatement = conn
          .prepareStatement(sql);
      pStatement.execute();
      tablesCreated += "player:";
      System.out.println("table created successfully...");
//----

      sql = "CREATE TABLE IF NOT EXISTS personal_match"
          + "("
          + " pmatch_id integer primary key auto_increment,"
          + " player_id integer NOT NULL,"
          + " location varchar(25) NOT NULL,"
          + " game_date DATE NOT NULL,"
          + " start_at TIME NOT NULL,"
          + " end_at TIME NOT NULL,"
          + " game_type varchar(25) NOT NULL,"
          + " num_initial_players integer,"
          + " num_players_joined integer,"
          + " foreign key (player_id) references player(pid)"
          + ")";

      pStatement = conn
          .prepareStatement(sql);
      pStatement.execute();
      tablesCreated += "personal_match:";
      System.out.println("table created successfully...");


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


  public String getTablesCreated() {
    return tablesCreated;
  }

}
