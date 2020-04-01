package sportsmate.menus;

import java.util.NoSuchElementException;

public class PersonalTeamMenu extends AbstractMenu {
  /**
   * Displays Personal Team menu.
   */
  @Override
  public String[] displayMenu() {
          getPrompt("\nEnter 1 to Create a Personal Match\nEnter 2 to Join a Personal Match"
              + "\nEnter 3 to Show Personal Matches I have joined"
              + "\nEnter 4 to go to previous menu"
              + "\nEnter 5 to Exit\n");

    try {
       do {
          System.out.printf("%n> ");
          selection = getScanner().next();
       } while ((!selection.equals("1")) && (!selection.equals("2")) && (!selection.equals("3")) &&
      (!selection.equals("4")) && (!selection.equals("5")));
    } catch (NoSuchElementException noSuchElementException) {
        System.err.println("Invalid input. Terminating.");
      }

    String[] str = {selection};
    return str;
  }
}
