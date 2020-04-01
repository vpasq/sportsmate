package sportsmate.menus;

import sportsmate.dao.DAO;
import sportsmate.dao.LoginDAO;
import sportsmate.dao.PersonalMatchDAO;
import sportsmate.dao.PlayerDAO;

public class MenuController {
  private AbstractMenu menu;
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
   * Checks user menu selection.
   *
   * @param selectionArr the user input array
   */
  private void checkMenuSelection(String[] selectionArr) {

    if (menu instanceof MainMenu) {
      switch (selectionArr[0]) {
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

    else if (menu instanceof RegisterMenu) {
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

    else if (menu instanceof LogInMenu) {
      String username = selectionArr[0];
      String password = selectionArr[1];
      LoginDAO loginDao = new LoginDAO();
      boolean userExists = loginDao.authLogin(username, password);
      if (userExists) {
        menu.getPrompt("\n" + menu.getLineBreak(username.length(), 39) + "\nHello " + username + ", "
            + "you have succesfully logged in!\n");
        loggedInUserID = loginDao.getLoggedInUserID();
        setMenu(new MainSubMenu());
        displayMenu();
      } else {
        menu.getPrompt("\n" + menu.getLineBreak(40) + "\nUsername and/or Password Does Not Exist!\n");
        System.out.println("\nExiting...\n");
        System.exit(1);
      }
    }

    // added by vp
    else if (menu instanceof MainSubMenu) {
      switch (selectionArr[0]) {
        case "1":
          setMenu(new PersonalTeamMenu());
          displayMenu();
          break;
        case "2":
          setMenu(new TeamMatchMenu());
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

    else if (menu instanceof LoginOrExitMenu) {
      switch (selectionArr[0]) {
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

    else if (menu instanceof PersonalTeamMenu) {
      switch (selectionArr[0]) {
        case "1":
          setMenu(new CreatePersonalMatchMenu());
          displayMenu();
          break;
        case "2":
          setMenu(new JoinPersonalMatchMenu());
          displayMenu();
          break;
        case "3":
          PersonalMatchDAO personalMatchDAO = new PersonalMatchDAO();
          personalMatchDAO.showPersonalMatchesJoined(loggedInUserID);
          setMenu(new PersonalTeamMenu());
          displayMenu();
          break;
        case "4":
          setMenu(new MainSubMenu());
          displayMenu();
          break;
        case "5":
          System.out.println("\nExiting...\n");
          System.exit(1);
          break;
        default:
          break;
      }
    }

    else if (menu instanceof CreatePersonalMatchMenu) {
      String location = selectionArr[0];
      String game_date = selectionArr[1];
      String startAt = selectionArr[2];
      String endAt = selectionArr[3];
      String game_type = selectionArr[4];
      int num_initial_players = Integer.parseInt(selectionArr[5]);
      PersonalMatchDAO personalMatchDAO = new PersonalMatchDAO();
      personalMatchDAO.createPersonalMatch(loggedInUserID, location, game_date, startAt, endAt,
          game_type, num_initial_players);
      setMenu(new PersonalTeamMenu());
      displayMenu();
    }

    else if (menu instanceof JoinPersonalMatchMenu) {
      switch (selectionArr[0]) {
        case "1":
          PersonalMatchDAO personalMatchDAO = new PersonalMatchDAO();
          personalMatchDAO.listAllPersonalMatches(loggedInUserID);
          setMenu(new MainSubMenu());
          displayMenu();
          break;
        case "2":
          //setMenu(new SearchPersonalMatchesByLocation());
          //displayMenu();
          break;
        case "3":
          System.out.println("\nExiting...\n");
          System.exit(1);
          break;
        default:
          break;
      }
    }


    else if (menu instanceof TeamMatchMenu) {
      switch (selectionArr[0]) {
        case "1":

          //setMenu(new CreateTeam());
          displayMenu();
          break;
        case "2":
          //setMenu(new JoinTeam());
          displayMenu();
          break;
        case "3":
          PersonalMatchDAO personalMatchDAO = new PersonalMatchDAO();
          personalMatchDAO.showPersonalMatchesJoined(loggedInUserID);
          setMenu(new PersonalTeamMenu());
          displayMenu();
          break;
        case "4":
          setMenu(new MainSubMenu());
          displayMenu();
          break;
        case "5":
          System.out.println("\nExiting...\n");
          System.exit(1);
          break;
        default:
          break;
      }
    }

  }

  public int getLoggedInUserID() {
    return loggedInUserID;
  }
}
