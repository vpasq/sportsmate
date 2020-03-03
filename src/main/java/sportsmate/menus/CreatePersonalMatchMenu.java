package sportsmate.menus;


import java.util.NoSuchElementException;

public class CreatePersonalMatchMenu extends AbstractMenu {

  /**
   * Displays menu to create a personal match.
   */
  @Override
  public String[] displayMenu() {
    String location;
    String game_date;
    String startAt;
    String endAt;
    String game_type;
    String num_initial_players;

    getPrompt(getLineBreak(27) + "\nCreate a new Personal Match\n\n");

    getPrompt("Enter the location of the game: ");
    location = getScanner().nextLine();

    getPrompt("Enter the date of the game (example format: 2020-03-01): ");
    game_date = getScanner().nextLine();

    getPrompt("Enter the start time of the game (example format: 08:00:00): ");
    startAt = getScanner().nextLine();

    getPrompt("Enter the end time of the game (example format: 10:00:00): ");
    endAt = getScanner().nextLine();

    getPrompt("Enter the game type: ");
    game_type = getScanner().nextLine();

    getPrompt("Enter the number of initial players: ");
    num_initial_players = getScanner().nextLine();


    String[] str = {location, game_date, startAt, endAt, game_type, num_initial_players};
    return str;
  }
}

