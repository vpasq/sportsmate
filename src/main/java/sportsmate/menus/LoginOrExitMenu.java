package sportsmate.menus;

import java.util.NoSuchElementException;

public class LoginOrExitMenu extends AbstractMenu {

  /**
   * Displays menu to log in.
   */
  @Override
  public String[] displayMenu() {
    getPrompt("\n" + getLineBreak(44) + "\nYou have successfully created a new account!\n");
    getPrompt("\nEnter 1 to Log In Now!\nEnter 2 to exit\n");

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
