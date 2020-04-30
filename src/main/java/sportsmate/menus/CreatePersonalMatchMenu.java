package sportsmate.menus;


import sportsmate.dao.PersonalMatchDAO;
import sportsmate.dao.GymDAO;
import sportsmate.dao.TeamMatchDAO;
import sportsmate.utilty.check;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;

public class CreatePersonalMatchMenu extends AbstractMenu {

  /**
   * Displays menu to create a personal match.
   */
  @Override
  public String[] displayMenu() {
    String gymID;
    String game_date;
    String startAt;
    String endAt;
    String game_type;
    String num_initial_players;

    GymDAO gymDAO = new GymDAO();
    gymDAO.listAllGyms();

    getPrompt("\n" + getLineBreak(27) + "\nCreate a new Personal Match\n" +
        getLineBreak(27) + "\n");

    getPrompt("\nEnter Gym ID to Reserve the Gymnasium for this Match:\n"
        + "(See list above): ");
    gymID = getScanner().nextLine();


    getPrompt("Enter Date of the Match (format: mm-dd-yyyy): ");

    game_date = getScanner().nextLine();
    //check date use function in check class
    // created by zhengkang fan
    boolean check_date = check.check_date_of_day (game_date);
    while (check_date == false){
      getPrompt("You entered an illegal or previous date. \n\nPlease enter the date of the game (format: mm-dd-yyyy): ");
      game_date  = getScanner().nextLine();
      check_date = check.check_date_of_day (game_date);
    }


    getPrompt("Enter Start Time of the Match (example format: 08:00:00): ");
    startAt = getScanner().nextLine();
    //check time use function in check class
    // created by zhengkang fan
    boolean check_start_time = check.check_date_in_the_day (startAt);
    while (check_start_time == false){
      getPrompt("You entered an illegal time. \n\nPlease enter the start time of game (format: HH:mm:ss): ");
      startAt  = getScanner().nextLine();
      check_start_time = check.check_date_in_the_day (startAt);
    }

    getPrompt("Enter End Time of the Match (example format: 10:00:00): ");
    endAt = getScanner().nextLine();
    //check time is after start time or not
    // created by zhengkang fan
    boolean check_end_time;
    SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");// import time format
    try {
      sd.setLenient(false);
      Date start = sd.parse(startAt);//change str into date
      Date end = sd.parse(endAt);//change str into date
      check_end_time = start.before(end) && end.after(start);//check time is after start time or not
    }
    catch (Exception e) {
      check_end_time = false;
    }
    while (check_end_time == false){
      getPrompt("You entered an illegal time, the end time must after "+startAt+ ".\n\nPlease enter the end time " +
              "of the game (format: HH:mm:ss): ");
      endAt  = getScanner().nextLine();
      try {
        sd.setLenient(false);
        Date start = sd.parse(startAt);
        Date end = sd.parse(endAt);
        check_end_time = start.before(end) && end.after(start);
      }
      catch (Exception e) {
        check_end_time = false;
      }
    }

    getPrompt("Enter Game Type as Follows: \n 1. 1 on 1 \n 2. 2 on 2 \n 3. 3 on 3 \n 4. 4 on 4 \n 5. 5 on 5 " +
            "\nEnter Number for the Game Type: ");
    game_type = getScanner().nextLine();
    //check game type use function in check class
    // created by zhengkang fan
    String game_type_str = check.check_game_type (game_type);// change the input number into corresponding string
    while (game_type_str == "unlegal"){
      getPrompt("You entered a null value or illegal number of game type. \n\nPlease enter the number between 1 ~ 5 : ");
      game_type = getScanner().nextLine();
      game_type_str = check.check_game_type (game_type);
    }
    game_type = game_type_str;  // save the1 corresponding string

    getPrompt("Enter Number of Initial Players: ");
    num_initial_players = getScanner().nextLine();
    //check initial players function in check class
    // created by zhengkang fan
    boolean check_initial_players = check.isPostiveInt (num_initial_players );
    while (check_initial_players == false){
      getPrompt("You entered a null value or illegal number of initial players. \n\nPlease enter the number of initial players: ");
      num_initial_players = getScanner().nextLine();
      check_initial_players = check.isPostiveInt (num_initial_players );
    }


    String[] str = {gymID, game_date, startAt, endAt, game_type, num_initial_players};
    return str;
  }
}

