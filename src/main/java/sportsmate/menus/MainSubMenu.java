package sportsmate.menus;

import java.util.NoSuchElementException;

public class MainSubMenu extends AbstractMenu {

  /**
   * Displays Personal Team menu.
   */
  @Override
  public String[] displayMenu() {
    getPrompt("\nEnter 1 for Personal Match Menu"
        + "\nEnter 2 for Team Menu"
        + "\nEnter 3 for Team Match Menu"
        + "\nEnter 4 to Exit\n");

    try {
      do {
        System.out.printf("%n> ");
        selection = getScanner().next();
      } while ((!selection.equals("1"))
          && (!selection.equals("2"))
          && (!selection.equals("3"))
          && (!selection.equals("4")));
    } catch (NoSuchElementException noSuchElementException) {
      System.err.println("Invalid input. Terminating.");
    }

    String[] str = {selection};
    return str;
  }

}
