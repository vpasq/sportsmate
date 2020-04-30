package sportsmate.menus;

import java.util.NoSuchElementException;

public class PersonalMatchMenu extends AbstractMenu {
  /**
   * Displays Personal Team menu.
   */
  @Override
  public String[] displayMenu() {
          getPrompt("\nEnter 1 Create Personal Match"
              + "\nEnter 2 Join Personal Match"
              + "\nEnter 3 List Personal Matches I have Created"
              + "\nEnter 4 List Personal Matches I have Joined"
              + "\nEnter 5 List All Current Personal Matches"
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
