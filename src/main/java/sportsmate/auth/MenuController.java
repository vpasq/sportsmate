package sportsmate.auth;

import sportsmate.dao.DAO;
import sportsmate.dao.LoginDAO;
import sportsmate.dao.PlayerDAO;

public class MenuController {
  private AbstractMenu menu;
  private DAO dao;

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
    if (menu.getClass().getName().equals("sportsmate.auth.MainMenu")) {
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

    if (menu.getClass().getName().equals("sportsmate.auth.RegisterMenu")) {
      String userFirstName = selectionArr[0];
      String userLastName = selectionArr[1];
      String username = selectionArr[2];
      String pswd = selectionArr[3];
      PlayerDAO playerDao = new PlayerDAO();
      playerDao.createPlayer(userFirstName, userLastName, username, pswd);
      setMenu(new LoginOrExitMenu());
      displayMenu();
    }

    if (menu.getClass().getName().equals("sportsmate.auth.LogInMenu")) {
      String username = selectionArr[1];
      String pswd = selectionArr[2];
      LoginDAO loginDao = new LoginDAO();
      boolean UserExists = loginDao.authLogin(username, pswd);
      if (UserExists) {
        getPrompt("\n" + getLineBreak(username.length(), 39) + "\nHello " + username + ", "
            + "you have succesfully logged in!\n");
        setMenu(new PersonalTeamMenu());
        displayMenu();
      } else {
        getPrompt(getLineBreak(49) + "\nUsername and Password Combination Does Not Exist!");
        System.out.println("\nExiting...\n");
        System.exit(1);
      }
    }

    if (menu.getClass().getName().equals("sportsmate.auth.PersonalTeamMenu")) {
      switch (selection) {
        case "1":
        case "2":
        case "3":
          System.out.println("\nExiting...\n");
          System.exit(1);
          break;
        default:
          break;
        }
    }

    if (menu.getClass().getName().equals("sportsmate.auth.LoginOrExit")) {
      switch (selection) {
        case "1":
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
  }

  /**
   * Concatenates correct number of characters for line break.
   *
   * @param num number of initial characters in line break
   * @param usernameLength number of characters in username.
   * @return a line break with correct number of characters
   */
  protected String getLineBreak(int usernameLength, int num) {
    String lineBreak = "";
    for (int i = 0; i < usernameLength + num; i++) {
      lineBreak += "=";
    }
    return lineBreak;
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
  public void getPrompt(String str) {
    System.out.print(str);
  }

}