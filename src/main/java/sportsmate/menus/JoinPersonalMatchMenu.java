package sportsmate.menus;

import java.util.NoSuchElementException;

public class JoinPersonalMatchMenu extends AbstractMenu {

  /**
   * Displays menu to join a personal match.
   */
  @Override
  public String[] displayMenu() {

    getPrompt("\n" + getLineBreak(27) + "\nJoin a Personal Match\n\n");

    getPrompt("Enter 1 to List All Personal Matches\n"
        + "Enter 2 to Search Filtered Personal Matches\n" // Add by Hu 20200327
        + "Enter 3 to Exit\n");

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
