package sportsmate.menus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.Scanner;
import sportsmate.dao.DAO;
import sportsmate.dao.PersonalMatchDAO;


public class JoinPersonalMatchMenu extends AbstractMenu {

  /**
   * Displays menu to join a personal match.
   */
  @Override
  public String[] displayMenu() {

    PersonalMatchDAO personalMatchDAO = new PersonalMatchDAO();
    personalMatchDAO.listAllPersonalMatches();

    String[] str = {selection};
    return str;

  }
}
