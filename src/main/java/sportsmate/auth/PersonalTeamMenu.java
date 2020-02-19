package sportsmate.auth;

import java.util.NoSuchElementException;

public class PersonalTeamMenu extends AbstractMenu {
  /**
   * Displays Personal Team menu.
   */
  @Override
  public String[] displayMenu() {
          getPrompt("\nEnter 1 to Join a Personal Team\nEnter 2 to Create a Personal Team"
          + "\nEnter 3 to Exit");

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
