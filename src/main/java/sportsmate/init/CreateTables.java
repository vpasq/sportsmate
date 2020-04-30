package sportsmate.init;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import sportsmate.dao.DAO;
import sportsmate.dao.LoginDAO;

public class CreateTables extends DAO {
  private Connection conn;
  ResultSet generatedKeys;
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
          + " password varchar(25) NOT NULL,"
          + " unique key(username, password)"
          + ")";

      pStatement = conn
          .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pStatement.execute();
      tablesCreated += "user:";
      //System.out.println("table created successfully...");

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
      //System.out.println("table created successfully...");


      sql = "CREATE TABLE IF NOT EXISTS team"
          + "("
          + " team_id integer primary key auto_increment,"
          + " team_name varchar(25) NOT NULL,"
          + " admin_id integer NOT NULL,"
          + " foreign key (admin_id) references player(pid)"
          + ")";

      pStatement = conn
          .prepareStatement(sql);
      pStatement.execute();
      tablesCreated = "team:";
      //System.out.println("table created successfully...");


      sql = "CREATE TABLE IF NOT EXISTS team_match"
          + "("
          + " match_id integer primary key auto_increment,"
          + " host_id integer NOT NULL,"
          + " guest_id integer,"
          + " gym_id integer NOT NULL,"
          //+ " location varchar(25) NOT NULL,"
          + " game_date DATE NOT NULL,"
          + " start_at TIME NOT NULL,"
          + " end_at TIME NOT NULL,"
          + " foreign key (host_id) references team(team_id),"
          + " foreign key (guest_id) references team(team_id)"
          + ")";

      pStatement = conn
          .prepareStatement(sql);
      pStatement.execute();
      tablesCreated += "team_match:";
      //System.out.println("table created successfully...");


//      sql = "CREATE TABLE IF NOT EXISTS team_match_players"
//          + "("
//          + " tmplayer_id integer,"
//          + " t_id integer,"
//          + " primary key (tmplayer_id, t_id)"
//          + ")";

      // added: for TeamDAO.listTeamsJoined
      sql = "CREATE TABLE IF NOT EXISTS team_match_players"
          + "("
          + " tmplayer_id integer,"
          + " t_id integer,"
          + " primary key (tmplayer_id, t_id),"
          + " foreign key (tmplayer_id) references player (pid), "
          + " foreign key (t_id) references team (team_id)"
          + ")";

      pStatement = conn
          .prepareStatement(sql);
      pStatement.execute();
      tablesCreated += "team_match_players:";
      //System.out.println("table created successfully...");


      sql = "CREATE TABLE IF NOT EXISTS personal_match_gymnasium"
          + "("
          + " pmg_id  integer primary key auto_increment,"
          + " pmg_name varchar(25),"
          + " pmg_location varchar(25),"
          + " unique (pmg_name, pmg_location)"
          + ")";

      pStatement = conn.prepareStatement(sql);
      pStatement.execute();
      tablesCreated += "persoanl_match_gymnasium:";
      //System.out.println("table created successfully...")




      sql = "CREATE TABLE IF NOT EXISTS personal_match"
          + "("
          + " pmatch_id integer primary key auto_increment,"
          + " player_id integer NOT NULL,"
          + " gym_id integer NOT NULL,"
          + " game_date DATE NOT NULL,"
          + " start_at TIME NOT NULL,"
          + " end_at TIME NOT NULL,"
          + " game_type varchar(25) NOT NULL,"
          + " num_initial_players integer,"
          + " num_players_joined integer,"
          + " foreign key (player_id) references player(pid),"
          + " foreign key (gym_id) references personal_match_gymnasium(pmg_id)"
          + ")";

      pStatement = conn.prepareStatement(sql);
      pStatement.execute();
      tablesCreated += "personal_match:";
      //System.out.println("table created successfully...");

      sql = "CREATE TABLE IF NOT EXISTS personal_match_players"
          + "("
          + " p_id integer,"
          + " match_id integer,"
          + " primary key (p_id, match_id),"
          + " foreign key (p_id) references player(pid),"
          + " foreign key (match_id) references personal_match(pmatch_id)"
          + ")";

      pStatement = conn
          .prepareStatement(sql);
      pStatement.execute();
      tablesCreated += "personal_match_players:";
      //System.out.println("table created successfully...");









// =============== START INSERT DATA  =====================================================
      LoginDAO loginDao = new LoginDAO();
      boolean userExists;

      userExists = loginDao.authLogin("sally", "test");
      if (!userExists) {
        sql = "INSERT INTO user VALUES (default, ?,?,?,?,?)";
        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pStatement.setString(1, "Sally");
        pStatement.setString(2, "Blake");
        pStatement.setString(3, "female");
        pStatement.setString(4, "sally");
        pStatement.setString(5, "test");
        pStatement.executeUpdate();
        //System.out.printf("Inserted Row into %s%n", "user table");

        ResultSet generatedKeys = pStatement.getGeneratedKeys();
        generatedKeys.next();

        sql = "insert into player VALUES (default, ?)";
        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pStatement.setString(1, Integer.toString(generatedKeys.getInt(1)));
        pStatement.execute();
        //System.out.printf("Inserted Row into %s%n", "player table");
      }

      userExists = loginDao.authLogin("lucy", "test");
      if (!userExists) {
        sql = "INSERT INTO user VALUES (default, ?,?,?,?,?)";
        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pStatement.setString(1, "Lucy");
        pStatement.setString(2, "Treston");
        pStatement.setString(3, "female");
        pStatement.setString(4, "lucy");
        pStatement.setString(5, "test");
        pStatement.executeUpdate();
        //System.out.printf("Inserted Row into %s%n", "user table");

        ResultSet generatedKeys = pStatement.getGeneratedKeys();
        generatedKeys.next();

        sql = "insert into player VALUES (default, ?)";
        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pStatement.setString(1, Integer.toString(generatedKeys.getInt(1)));
        pStatement.execute();
        //System.out.printf("Inserted Row into %s%n", "player table");
      }

      userExists = loginDao.authLogin("john", "test");
      if (!userExists) {
        sql = "INSERT INTO user VALUES (default, ?,?,?,?,?)";
        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pStatement.setString(1, "John");
        pStatement.setString(2, "Smith");
        pStatement.setString(3, "male");
        pStatement.setString(4, "john");
        pStatement.setString(5, "test");
        pStatement.executeUpdate();
        //System.out.printf("Inserted Row into %s%n", "user table");

        ResultSet generatedKeys = pStatement.getGeneratedKeys();
        generatedKeys.next();

        sql = "insert into player VALUES (default, ?)";
        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pStatement.setString(1, Integer.toString(generatedKeys.getInt(1)));
        pStatement.execute();
        //System.out.printf("Inserted Row into %s%n", "player table");
      }

      userExists = loginDao.authLogin("derik", "test");
      if (!userExists) {
        sql = "INSERT INTO user VALUES (default, ?,?,?,?,?)";
        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pStatement.setString(1, "Derick");
        pStatement.setString(2, "Dhamer");
        pStatement.setString(3, "male");
        pStatement.setString(4, "derick");
        pStatement.setString(5, "test");
        pStatement.executeUpdate();
        //System.out.printf("Inserted Row into %s%n", "user table");

        ResultSet generatedKeys = pStatement.getGeneratedKeys();
        generatedKeys.next();

        sql = "insert into player VALUES (default, ?)";
        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pStatement.setString(1, Integer.toString(generatedKeys.getInt(1)));
        pStatement.execute();
        //System.out.printf("Inserted Row into %s%n", "player table");
      }

      userExists = loginDao.authLogin("jerry", "test");
      if (!userExists) {
        sql = "INSERT INTO user VALUES (default, ?,?,?,?,?)";
        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pStatement.setString(1, "Jerry");
        pStatement.setString(2, "Dallen");
        pStatement.setString(3, "male");
        pStatement.setString(4, "jerry");
        pStatement.setString(5, "test");
        pStatement.executeUpdate();
        //System.out.printf("Inserted Row into %s%n", "user table");

        ResultSet generatedKeys = pStatement.getGeneratedKeys();
        generatedKeys.next();

        sql = "insert into player VALUES (default, ?)";
        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pStatement.setString(1, Integer.toString(generatedKeys.getInt(1)));
        pStatement.execute();
      }



      //=====================================================================


      ResultSet resultSet;

      sql = "select * "
          + "from personal_match_gymnasium pmg "
          + "where pmg.pmg_id =1 "
          + "and pmg.pmg_name=? "
          + "and pmg.pmg_location=? "
          + "";
      pStatement = conn.prepareStatement(sql);
      pStatement.setString(1, "Mullen Park");
      pStatement.setString(2, "Boston");
      resultSet = pStatement.executeQuery();

      if (!resultSet.next()) {
        sql = "INSERT INTO personal_match_gymnasium VALUES (default, ?, ?)";
        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pStatement.setString(1, "Mullen Park");
        pStatement.setString(2, "Boston");
        pStatement.executeUpdate();
      }


      sql = "select * "
          + "from personal_match_gymnasium pmg "
          + "where pmg.pmg_name=? "
          + "and pmg.pmg_location=? "
          + "";
      pStatement = conn.prepareStatement(sql);
      pStatement.setString(1, "Riverside Park");
      pStatement.setString(2, "Boston");
      resultSet = pStatement.executeQuery();

      if (!resultSet.next()) {
        sql = "INSERT INTO personal_match_gymnasium VALUES (default, ?, ?)";
        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pStatement.setString(1, "Riverside Park");
        pStatement.setString(2, "Boston");
        pStatement.executeUpdate();
      }


      sql = "select * "
          + "from personal_match_gymnasium pmg "
          + "where pmg.pmg_name=? "
          + "and pmg.pmg_location=? "
          + "";
      pStatement = conn.prepareStatement(sql);
      pStatement.setString(1, "Seaside Park");
      pStatement.setString(2, "Boston");
      resultSet = pStatement.executeQuery();

      if (!resultSet.next()) {
        sql = "INSERT INTO personal_match_gymnasium VALUES (default, ?, ?)";
        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pStatement.setString(1, "Seaside Park");
        pStatement.setString(2, "Boston");
        pStatement.executeUpdate();
      }






      //======================================================================








//      sql = "select * from personal_match where player_id=? and gym_id=? and game_date=?"
//          + "and start_at=? and end_at=? and game_type=? and num_initial_players=?";
//      pStatement = conn.prepareStatement(sql);
//      pStatement.setInt(1, 1);
//      pStatement.setInt(2, 1);
//      pStatement.setDate(3, Date.valueOf("2020-09-04"));
//      pStatement.setTime(4, Time.valueOf("09:00:00"));
//      pStatement.setTime(5, Time.valueOf("11:00:00"));
//      pStatement.setString(6, "1");
//      pStatement.setInt(7, 1 );
//      //pStatement.setInt(8, 0 );
//      resultSet = pStatement.executeQuery();
//
//      if (!resultSet.next()) {
//        sql = "INSERT INTO personal_match VALUES (default, ?,?,?,?,?,?,?,?)";
//        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//        pStatement.setInt(1, 1);
//        pStatement.setInt(2, 1);
//        pStatement.setDate(3, Date.valueOf("2020-09-04"));
//        pStatement.setTime(4, Time.valueOf("09:00:00"));
//        pStatement.setTime(5, Time.valueOf("11:00:00"));
//        pStatement.setString(6, "1");
//        pStatement.setInt(7, 1);
//        pStatement.setInt(8, 0);
//        pStatement.executeUpdate();
//        //System.out.println("\nYou have successfully created a personal match!");
//      }
//
//
//      sql = "select * from personal_match where player_id=? and gym_id=? and game_date=?"
//          + "and start_at=? and end_at=? and game_type=? and num_initial_players=?";
//      pStatement = conn.prepareStatement(sql);
//      pStatement.setInt(1, 2);
//      pStatement.setInt(2, 2);
//      pStatement.setDate(3, Date.valueOf("2020-10-10"));
//      pStatement.setTime(4, Time.valueOf("09:00:00"));
//      pStatement.setTime(5, Time.valueOf("11:00:00"));
//      pStatement.setString(6, "3");
//      pStatement.setInt(7, 3 );
//      //pStatement.setInt(8, 0 );
//      resultSet = pStatement.executeQuery();
//
//      if (!resultSet.next()) {
//        sql = "INSERT INTO personal_match VALUES (default, ?,?,?,?,?,?,?,?)";
//        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//        pStatement.setInt(1, 2);
//        pStatement.setInt(2, 2);
//        pStatement.setDate(3, Date.valueOf("2020-10-10"));
//        pStatement.setTime(4, Time.valueOf("09:00:00"));
//        pStatement.setTime(5, Time.valueOf("11:00:00"));
//        pStatement.setString(6, "3");
//        pStatement.setInt(7, 3);
//        pStatement.setInt(8, 0);
//        pStatement.executeUpdate();
//        //System.out.println("\nYou have successfully created a personal match!");
//      }
//
//
//      sql = "select * from personal_match where player_id=? and gym_id=? and game_date=?"
//          + "and start_at=? and end_at=? and game_type=? and num_initial_players=?";
//      pStatement = conn.prepareStatement(sql);
//      pStatement.setInt(1, 3);
//      pStatement.setInt(2, 3);
//      pStatement.setDate(3, Date.valueOf("2020-03-22"));
//      pStatement.setTime(4, Time.valueOf("08:00:00"));
//      pStatement.setTime(5, Time.valueOf("11:00:00"));
//      pStatement.setString(6, "3");
//      pStatement.setInt(7, 3);
//      //pStatement.setInt(8, 0);
//      resultSet = pStatement.executeQuery();
//
//      if (!resultSet.next()) {
//        sql = "INSERT INTO personal_match VALUES (default, ?,?,?,?,?,?,?,?)";
//        pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//        pStatement.setInt(1, 3);
//        pStatement.setInt(2, 3);
//        pStatement.setDate(3, Date.valueOf("2020-03-22"));
//        pStatement.setTime(4, Time.valueOf("08:00:00"));
//        pStatement.setTime(5, Time.valueOf("11:00:00"));
//        pStatement.setString(6, "3");
//        pStatement.setInt(7, 3);
//        pStatement.setInt(8, 0);
//        pStatement.executeUpdate();
//        //System.out.println("\nYou have successfully created a personal match!");
//      }




// =============== END INSERT DATA  =====================================================

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


  public String getTablesCreated() {
    return tablesCreated;
  }

}
