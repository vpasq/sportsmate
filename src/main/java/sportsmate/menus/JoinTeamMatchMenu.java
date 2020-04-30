package sportsmate.menus;

import java.util.NoSuchElementException;
import sportsmate.dao.TeamMatchDAO;

public class JoinTeamMatchMenu extends AbstractMenu {
  private int loggedInUser;

  public JoinTeamMatchMenu(int loggedInUser) {
    this.loggedInUser = loggedInUser;
  }

  /**
   * Displays menu to join a team match.
   */
  @Override
  public String[] displayMenu() {
    String guestTeamID;

    TeamMatchDAO teamMatchDAO = new TeamMatchDAO();
    teamMatchDAO.listAllTeamMatches();

    getPrompt("\n" + getLineBreak(17) + "\nJoin a Team Match\n\n");

    getPrompt("Enter the Team ID of a team where you are the admin.\n"
        + "(See list above): ");
    guestTeamID = getScanner().nextLine();


// vp

    int guestTeamIDTemp = Integer.parseInt(guestTeamID);

    while (!teamMatchDAO.isAdmin(loggedInUser, guestTeamIDTemp)) {
      System.out.println("\nWARNING! YOU CANNOT CREATE THIS MATCH BECAUSE "
          + "YOU ARE NOT THE ADMIN OF THE TEAM");
      getPrompt("\nEnter a Team ID where you are the admin to create the guest team for "
          + "the match (Ctrl+C to Exit).\n"
          + "(See list above): ");
      guestTeamID = getScanner().nextLine();
      guestTeamIDTemp = Integer.parseInt(guestTeamID);
    }
    guestTeamID  = String.valueOf(guestTeamIDTemp);

// vp


    getPrompt("\nEnter the Match ID that you want your team to join: ");
    String teamMatchID = getScanner().nextLine();

    String[] str = {guestTeamID, teamMatchID};
    return str;
  }

}
