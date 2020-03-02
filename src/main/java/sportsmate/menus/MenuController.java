package sportsmate.menus;

import sportsmate.dao.DAO;
import sportsmate.dao.LoggedInUserDAO;
import sportsmate.dao.LoginDAO;
import sportsmate.dao.PersonalMatchDAO;
import sportsmate.dao.PlayerDAO;

public class MenuController {
  private AbstractMenu menu;
  private DAO dao;
  private int loggedInUserID;

  /**
   * Sets the menu reference type.
   *
   * @param menu the menu reference type
   */
  public void setMenu(AbstractMenu menu) {
    this.menu = menu;
  }

  /**
   * Delegate to the subtype menus
   */
  public void displayMenu() {
    checkMenuSelection(menu.displayMenu());
  }

  /**
   * Helper method checks user menu selection.
   *
   * @param selectionArr the user input array
   */
  private void checkMenuSelection(String[] selectionArr) {
    String selection = selectionArr[0];

    if (menu instanceof MainMenu) {
      switch (selection) {
        case "1":
          setMenu(new RegisterMenu());
          displayMenu();
          break;
        case "2":
          setMenu(new LogInMenu());
          displayMenu();
          break;
        case "3":
          System.out.println("\nExiting...\n");
          System.exit(1);
          break;
        default:
          break;
      }
    }

    if (menu instanceof RegisterMenu) {
      String userFirstName = selectionArr[0];
      String userLastName = selectionArr[1];
      String username = selectionArr[2];
      String userGender = selectionArr[3];
      String password = selectionArr[4];
      PlayerDAO playerDao = new PlayerDAO();
      playerDao.createPlayer(userFirstName, userLastName, userGender, username, password);
      setMenu(new LoginOrExitMenu());
      displayMenu();
    }

    if (menu instanceof LogInMenu) {
      String username = selectionArr[0];
      String pswd = selectionArr[1];
      LoginDAO loginDao = new LoginDAO();
      boolean UserExists = loginDao.authLogin(username, pswd);
      if (UserExists) {
        menu.getPrompt("\n" + menu.getLineBreak(username.length(), 39) + "\nHello " + username + ", "
            + "you have succesfully logged in!\n");
        loggedInUserID = loginDao.getLoggedInUserID();
        setMenu(new PersonalTeamMenu());
        displayMenu();
      } else {
        menu.getPrompt("\n" + menu.getLineBreak(40) + "\nUsername and/or Password Does Not Exist!\n");
        System.out.println("\nExiting...\n");
        System.exit(1);
      }
    }

    if (menu instanceof LoginOrExitMenu) {
      switch (selection) {
        case "1":
          setMenu(new LogInMenu());
          displayMenu();
          break;
        case "2":
          System.out.println("\nExiting...\n");
          System.exit(1);
          break;
        default:
          break;
      }
    }

    if (menu instanceof PersonalTeamMenu) {
      switch (selection) {
        case "1":
          setMenu(new CreatePersonalMatchMenu());
          displayMenu();
          break;
        case "2":
        case "3":
          System.out.println("\nExiting...\n");
          System.exit(1);
          break;
        default:
          break;
      }
    }

    if (menu instanceof CreatePersonalMatchMenu) {
      String location = selectionArr[0];
      String game_date = selectionArr[1];
      String startAt = selectionArr[2];
      String endAt = selectionArr[3];
      String game_type = selectionArr[4];
      String num_current_players = selectionArr[5];

//      System.out.println("loggedInUserID: " + loggedInUserID + "\n");
//      System.out.println("location: " + location + "\n");
//      System.out.println("game_date: " + game_date + "\n");
//      System.out.println("startAt: " + startAt + "\n");
//      System.out.println("endAt: " + endAt + "\n");
//      System.out.println("game_type: " + game_type + "\n");
//      System.out.println("num_current_players: " + num_current_players + "\n");

      PersonalMatchDAO personalMatchDAO = new PersonalMatchDAO();
      personalMatchDAO.createPersonalMatch(loggedInUserID, location, game_date, startAt, endAt, game_type, num_current_players);
      setMenu(new PersonalTeamMenu());
      displayMenu();
    }

  }


  private int getLoggedInUserID() {
    return loggedInUserID;
  }

}
