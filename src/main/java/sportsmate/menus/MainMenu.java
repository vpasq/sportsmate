package sportsmate.menus;

import java.util.NoSuchElementException;

public class MainMenu extends AbstractMenu {

  /**
   *  Displays main menu.
   */
  @Override
  public String[] displayMenu() {

    getPrompt("\nWelcome to SportsMate!\n" + getLineBreak(22));
    getPrompt("Enter 1 to Create a New Account\nEnter 2 to Log In\nEnter 3 to Exit");

    try {
      do {
        System.out.printf("%n> ");
        selection = getScanner().next();
      } while ((!selection.equals("1")) && (!selection.equals("2")) && (!selection.equals("3")));
    } catch (NoSuchElementException noSuchElementException) {
      System.err.println("Invalid input. Terminating.");
    }

    String[] str = {selection};
    return str;
  }

  /**
   * User prompt.
   *
   * @param str string to prompt user
   */
  public void getPrompt(String str) {
    System.out.println(str);
    System.out.println();
  }
}
