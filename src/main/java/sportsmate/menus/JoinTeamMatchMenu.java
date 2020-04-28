package sportsmate.menus;

import java.util.NoSuchElementException;
import sportsmate.dao.TeamMatchDAO;

public class JoinTeamMatchMenu extends AbstractMenu {

  /**
   * Displays menu to join a team match.
   */
  @Override
  public String[] displayMenu() {

    TeamMatchDAO teamMatchDAO = new TeamMatchDAO();
    teamMatchDAO.listAllTeamMatches();

    getPrompt("\n" + getLineBreak(17) + "\nJoin a Team Match\n\n");

    getPrompt("Enter the Team ID of a team where you are the admin.\n"
        + "(See list above): ");
    String guestTeamID = getScanner().nextLine();

    getPrompt("\nEnter the Match ID that you want your team to join: ");
    String teamMatchID = getScanner().nextLine();

    String[] str = {guestTeamID, teamMatchID};
    return str;
  }

}
