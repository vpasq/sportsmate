package sportsmate.menus;

import java.util.NoSuchElementException;

public class MainSubMenu extends AbstractMenu {

  /**
   * Displays Personal Team menu.
   */
  @Override
  public String[] displayMenu() {


    getPrompt("\n" + getLineBreak(9) + "\nMain Menu\n" + getLineBreak(9) + "\n");

    getPrompt("\nEnter 1 Personal Match Menu"
        + "\nEnter 2 Team Menu"
        + "\nEnter 3 Team Match Menu"
        + "\nEnter 4 Log in to or Create Another Account"
        + "\nEnter 5 Exit\n");

    try {
      do {
        System.out.printf("%n> ");
        selection = getScanner().next();
      } while ((!selection.equals("1"))
          && (!selection.equals("2"))
          && (!selection.equals("3"))
          && (!selection.equals("4"))
          && (!selection.equals("5")));
    } catch (NoSuchElementException noSuchElementException) {
      System.err.println("Invalid input. Terminating.");
    }

    String[] str = {selection};
    return str;
  }

}
