package sportsmate.menus;

import java.util.NoSuchElementException;

public class TeamMatchMenu extends AbstractMenu {

  /**
   * Displays Team Match menu.
   */
  @Override
  public String[] displayMenu() {
    getPrompt("\nEnter 1 Create a Team Match"
        + "\nEnter 2 Join a Team Match"
        //+ "\nEnter 3 to Cancel a Team Match"
        + "\nEnter 3 List Team Matches I have created"
        + "\nEnter 4 List Team Matches my Team(s) have joined"
        + "\nEnter 5 List All Team Matches"
        + "\nEnter 6 Main Menu"
        + "\nEnter 7 Exit\n");
    try {
      do {
        System.out.printf("%n> ");
        selection = getScanner().next();
      } while ((!selection.equals("1"))
          && (!selection.equals("2"))
          && (!selection.equals("3"))
          && (!selection.equals("4"))
          && (!selection.equals("5"))
          && (!selection.equals("6"))
          && (!selection.equals("7")));
    } catch (NoSuchElementException noSuchElementException) {
      System.err.println("Invalid input. Terminating.");
    }

    String[] str = {selection};
    return str;
  }
}
