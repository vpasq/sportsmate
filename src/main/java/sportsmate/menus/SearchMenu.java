package sportsmate.menus;

import java.util.NoSuchElementException;

public class SearchMenu extends AbstractMenu{

  /**
   * Displays menu to search menu.
   */
  @Override
  public String[] displayMenu() {

    getPrompt("\n" + getLineBreak(11) + "\nSearch Menu\n\n");

    getPrompt("Enter 1 list all Personal Matches\n"
        + "Enter 2 list all Teams\n" // Add by vp
        + "Enter 3 list all Team Matches\n" // Add by vp
        + "Enter 4 filtered search of Personal Matches\n" // Add by Hu 20200327
        + "Enter 5 Main Menu\n" // Add by vp
        + "Enter 6 Exit\n"); // Add by vp

    try {
      do {
        System.out.printf("%n> ");
        selection = getScanner().next();
      } while ((!selection.equals("1"))
          && (!selection.equals("2"))
          && (!selection.equals("3"))
          && (!selection.equals("4"))
          && (!selection.equals("5"))
          && (!selection.equals("6")));
    } catch (NoSuchElementException noSuchElementException) {
      System.err.println("Invalid input. Terminating.");
    }

    String[] str = {selection};
    return str;

  }



}
