package sportsmate.menus;

import java.io.Console;
import java.util.Arrays;
import sportsmate.utilty.check;

public class RegisterMenu extends AbstractMenu {

  /**
   * Displays menu to register a new account.
   */
  @Override
  public String[] displayMenu() {
    String userFirstName;
    String userLastName;
    String userGender;

    getPrompt(getLineBreak(20) + "\nCreate a new account\n\n");

    getPrompt("Enter your first name: ");
    userFirstName = getScanner().nextLine();
    //check first name use function in check class
    // created by zhengkang fan
    boolean check_first_name = check.isSpecialChar(userFirstName)||check.isNumberChar(userFirstName);
    while (check_first_name){
      getPrompt("You entered a null value or included special charater(s) or number(s)! \n\nPlease enter your first name again:");
      userFirstName = getScanner().nextLine();
      check_first_name = check.isSpecialChar(userFirstName)||check.isNumberChar(userFirstName);
    }

    getPrompt("Enter your last name: ");
    userLastName = getScanner().nextLine();
    //check last name use function in check class
    // created by zhengkang fan
    boolean check_last_name = check.isSpecialChar(userLastName)||check.isNumberChar(userLastName);
    while (check_last_name){
      getPrompt(" You entered a null value or included special charater(s) or number(s)! \n\nPlease enter your last name again:");
      userLastName = getScanner().nextLine();
      check_last_name = check.isSpecialChar(userLastName)||check.isNumberChar(userLastName);
    }

    getPrompt("Enter your gender(eg: female, male, secret): ");
    userGender = getScanner().nextLine();
    //check gender use function in check class
    // created by zhengkang fan
    boolean check_gender = check.isgenderChar(userGender);
    while (check_gender == false){
      getPrompt("You entered a null value! \n\nPlease enter your gender again:");
      userGender = getScanner().nextLine();
      check_gender = check.isgenderChar(userGender);
    }

    Console console = System.console();
    if (console == null) {
      System.out.println("Couldn't get Console instance");
      System.exit(1);
    }

    String username = console.readLine("Enter a username: ");
    //check username use function in check class
    // created by zhengkang fan
    boolean check_username = check.isSpecialChar(username);
    while (check_username){
      getPrompt("You entered a null value or included special charater(s)！\n\nPlease enter your username again:");
      username = getScanner().nextLine();
      check_username = check.isSpecialChar(username);
    }

    char[] passwordArray = console.readPassword("Enter your secret password: ");

    char[] passwordArrayConfirm = console.readPassword("Confirm your secret password: ");

    // Confirm passwords match
    while (!Arrays.equals(passwordArray, passwordArrayConfirm)) {
      getPrompt("\n" + getLineBreak(35) + "\nPASSWORDS DO NOT MATCH - TRY AGAIN!\n\n");
      passwordArray = console.readPassword("Enter your secret password: ");
      passwordArrayConfirm = console.readPassword("Confirm your secret password: ");
    }

    String[] str = {userFirstName, userLastName, username, userGender, new String(passwordArray)};

    // Immediately overwrite password array elements with a filler value.
    Arrays.fill(passwordArray, 'x');

    return str;
  }

}
