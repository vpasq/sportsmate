package sportsmate.menus;

import java.util.NoSuchElementException;

public class LoginOrExitMenu extends AbstractMenu {

  /**
   * Displays menu to log in.
   */
  @Override
  public String[] displayMenu() {
    getPrompt("\nYOU HAVE SUCCESFULLY CREATED A NEW ACCOUNT!\n\n");

    getPrompt(getLineBreak(6) + "\nLog In\n");
    System.out.println("======\n");

    getPrompt("Enter 1 Log In\nEnter 2 exit\n");

    try {
      do {
        System.out.printf("%n> ");
        selection = getScanner().next();
      } while ((!selection.equals("1")) && (!selection.equals("2")));
    } catch (NoSuchElementException noSuchElementException) {
      System.err.println("Invalid input. Terminating.");
    }

    String[] str = {selection};
    return str;
  }
}
