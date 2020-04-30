package sportsmate.menus;

import java.util.Scanner;

public abstract class AbstractMenu {
  //protected int selection;
  protected String selection;
  private Scanner sc = new Scanner(System.in);

  /**
   * Scanner to read user input.
   *
   * @return Scanner
   */
  Scanner getScanner() {
    return sc;
  }

  /**
   * Concatenates correct number of characters for line break.
   *
   * @param num number of initial characters in line break
   * @param usernameLength number of characters in username.
   * @return a line break with correct number of characters
   */
  protected String getLineBreak(int usernameLength, int num) {
    String lineBreak = "";
    for (int i = 0; i < usernameLength + num; i++) {
      lineBreak += "=";
    }
    return lineBreak;
  }

  /**
   * Concatenates set number of characters for line break.
   *
   * @param num the number of characters in the line break
   * @return a line break with a set number of characters
   */
  public String getLineBreak(int num) {
    String lineBreak = "";
    for (int i = 0; i < num; i++) {
      lineBreak += "=";
    }
    return lineBreak;
  }

  /**
   * User prompt.
   *
   * @param str string to prompt user
   */
  public void getPrompt(String str) {
    System.out.print(str);
  }

  /**
   * Displays menu.
   *
   * @return menu selection
   */
  abstract String[] displayMenu();

}
