package sportsmate.menus;

import java.io.Console;


public class LogInMenu extends AbstractMenu {

  /**
   * Displays menu to log in.
   */
  @Override
  public String[] displayMenu() {
    Console console = System.console();

    if (console == null) {
      System.out.println("Couldn't get Console instance");
      System.exit(1);
    }

    getPrompt(getLineBreak(6) + "\nLog In\n" + getLineBreak(6) + "\n");
    String username = console.readLine("\nEnter Username: ");

    char[] passwordArray = console.readPassword("Enter Password: ");

    String[] str = {username, new String(passwordArray)};
    return str;
  }
}
