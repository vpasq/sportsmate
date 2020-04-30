package sportsmate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GymDAO extends DAO {
  private Connection conn;
  private String sql;
  private PreparedStatement pStatement;
  private int pmg_id;
  private String pmg_name;
  private String pmg_location;

  public void listAllGyms() {

    String sql = "select * from personal_match_gymnasium";

    try {
      conn = getConnection();
      PreparedStatement pStatement = conn.prepareStatement(sql);
      ResultSet resultSet = pStatement.executeQuery();

      getPrompt("\n" + getLineBreak(23) + "\nList of Available Gyms:\n"
      + getLineBreak(23) + "\n");

      while (resultSet.next()) {
        pmg_id = resultSet.getInt("pmg_id");
        pmg_name = resultSet.getString("pmg_name");
        pmg_location = resultSet.getString("pmg_location");

        System.out.println();
        System.out.println("Gym ID: " + pmg_id);
        System.out.println("Gym Name: " + pmg_name);
        System.out.println("Gym Location: " + pmg_location);

      }
      System.out.println();
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

  /**
   * Concatenates set number of characters for line break.
   *
   * @param num the number of characters in the line break
   * @return a line break with a set number of characters
   */
  protected String getLineBreak(int num) {
    String lineBreak = "";
    for (int i = 0; i < num; i++) {
      lineBreak += "=";
    }
    return lineBreak;
  }

  /**
   * User prompt.
   *
   * @param str string to prompt user
   */
  protected void getPrompt(String str) {
    System.out.print(str);
  }


}
