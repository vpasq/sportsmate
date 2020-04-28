package sportsmate.menus;

import sportsmate.dao.PersonalMatchDAO;
import sportsmate.dao.TeamDAO;

public class JoinTeamMenu extends AbstractMenu {


  /**
   * Displays menu to join team.
   */
  @Override
  public String[] displayMenu() {
//    String teamName;

//    getPrompt("\n" + getLineBreak(11) + "\nJoin a Team\n\n");
//
//    getPrompt("Enter the Team ID of the Team you would like to Join\n"
//        + "(See the list of current teams above)");

//    teamName = getScanner().nextLine();

    TeamDAO teamDAO = new TeamDAO();
    teamDAO.listAllTeams();

//    String[] str = {teamName};
    String[] str = {selection};
    return str;
  }

}

