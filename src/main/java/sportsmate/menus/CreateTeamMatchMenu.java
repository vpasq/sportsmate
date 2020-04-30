package sportsmate.menus;

import java.util.NoSuchElementException;
import sportsmate.dao.GymDAO;
import sportsmate.dao.TeamDAO;
import sportsmate.dao.TeamMatchDAO;

public class CreateTeamMatchMenu extends AbstractMenu {
  private int loggedInUser;

  public CreateTeamMatchMenu(int loggedInUser) {
    this.loggedInUser = loggedInUser;
  }

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

    getPrompt("\nEnter a Host Team ID Where You Are the Admin.\n"
        + "(See list above): ");
    hostTeamID = getScanner().nextLine();

    TeamMatchDAO teamMatchDAO = new TeamMatchDAO();
    int hostTeamIDTemp = Integer.parseInt(hostTeamID);

    while (!teamMatchDAO.checkIfTeamAdmin(loggedInUser, hostTeamIDTemp)) {
      System.out.println("\nWARNING! YOU CANNOT CREATE THIS MATCH BECAUSE "
          + "YOU ARE NOT THE ADMIN OF THE TEAM");
      getPrompt("\nPlease Enter a Host Team ID Where You Are the Admin (CTRL-D to EXIT): \n"
          + "(See list above): ");
      hostTeamID = getScanner().nextLine();
      hostTeamIDTemp = Integer.parseInt(hostTeamID);
    }

    hostTeamID  = String.valueOf(hostTeamIDTemp);

    getPrompt("\nEnter the Gym ID to Reserve the Gymnasium for this Match:\n"
        + "(See list above): ");
    gymID = getScanner().nextLine();

//    getPrompt("\nEnter location of Team Match Game: ");
//    location = getScanner().nextLine();

    getPrompt("\nEnter Date of the Team Match: (format: mm-dd-yyyy): ");
    gameDate = getScanner().nextLine();

    getPrompt("\nEnter Start Time of Team Match: (example format: 08:00:00): ");
    startAt = getScanner().nextLine();

    getPrompt("\nEnter End Time of Team Match: (example format: 10:00:00): ");
    endAt = getScanner().nextLine();

    String[] str = {hostTeamID, null, gymID, gameDate, startAt, endAt};
    return str;
  }

}
