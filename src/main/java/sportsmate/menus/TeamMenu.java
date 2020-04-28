package sportsmate.menus;

import java.util.NoSuchElementException;

public class TeamMenu extends AbstractMenu {

  /**
   * Displays Team menu.
   */
  @Override
  public String[] displayMenu() {
    getPrompt("\nEnter 1 to Create a Team"
        + "\nEnter 2 to Join a Team"
        + "\nEnter 3 Leave a Team"
        + "\nEnter 4 to List Teams I have created"
        + "\nEnter 5 to List Teams I have joined"
        + "\nEnter 6 to List All of the Teams"
        + "\nEnter 7 for Main Menu"
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
