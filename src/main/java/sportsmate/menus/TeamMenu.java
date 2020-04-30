package sportsmate.menus;

import java.util.NoSuchElementException;

public class TeamMenu extends AbstractMenu {

  /**
   * Displays Team menu.
   */
  @Override
  public String[] displayMenu() {
    getPrompt("\nEnter 1 Create a Team"
        + "\nEnter 2 Join Team"
        + "\nEnter 3 Leave Team"
        + "\nEnter 4 List Teams Created"
        + "\nEnter 5 List Teams Joined"
        + "\nEnter 6 List All Teams"
        + "\nEnter 7 Main Menu"
        + "\nEnter 8 to Exit\n");
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
          && (!selection.equals("7"))
          && (!selection.equals("8")));
    } catch (NoSuchElementException noSuchElementException) {
      System.err.println("Invalid input. Terminating.");
    }

    String[] str = {selection};
    return str;
  }
}
