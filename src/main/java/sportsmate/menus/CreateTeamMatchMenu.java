package sportsmate.menus;

import java.util.NoSuchElementException;
import sportsmate.dao.GymDAO;
import sportsmate.dao.TeamDAO;
import sportsmate.dao.TeamMatchDAO;

public class CreateTeamMatchMenu extends AbstractMenu {

  /**
   * Displays Create Team Match Menu.
   */
  @Override
  public String[] displayMenu() {
    String hostTeamID;
    String guestTeamID;
    String gymID;
    String gameDate;
    String startAt;
    String endAt;

//    TeamMatchDAO teamMatchDAO = new TeamMatchDAO();
//    teamMatchDAO.listAllTeamMatches();

    GymDAO gymDAO = new GymDAO();
    gymDAO.listAllGyms();

    getPrompt("\n" + getLineBreak(25) + "\nCreate a Team Match Game:\n");

    getPrompt("\nEnter a Host Team ID where you are the admin.\n"
        + "(See list above): ");

    hostTeamID = getScanner().nextLine();

    getPrompt("\nEnter the Gym ID to reserve the gymnasium for this match:\n"
        + "(See list above): ");
    gymID = getScanner().nextLine();

//    getPrompt("\nEnter location of Team Match Game: ");
//    location = getScanner().nextLine();

    getPrompt("\nEnter date of Team Match Game: (format: mm-dd-yyyy): ");
    gameDate = getScanner().nextLine();

    getPrompt("\nEnter the start time of Team Match Game: (example format: 08:00:00): ");
    startAt = getScanner().nextLine();

    getPrompt("\nEnter the end time of the game (example format: 10:00:00): ");
    endAt = getScanner().nextLine();

    String[] str = {hostTeamID, null, gymID, gameDate, startAt, endAt};
    return str;
  }

}
