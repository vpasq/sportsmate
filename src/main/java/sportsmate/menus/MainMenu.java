package sportsmate.menus;

import java.util.NoSuchElementException;

public class MainMenu extends AbstractMenu {

  /**
   *  Displays main menu.
   */
  @Override
  public String[] displayMenu() {

    System.out.println("\n\n======================");
    getPrompt("Welcome to SportsMate!\n" + getLineBreak(22));
    getPrompt("\n\nEnter 1 Log In\nEnter 2 Create a New Account\nEnter 3 Exit\n");

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

}
