package sportsmate.menus;

public class LeaveTeamMenu extends AbstractMenu {

  /**
   * Displays menu to leave team.
   */
  @Override
  public String[] displayMenu() {
    String teamName;

    getPrompt("\n" + getLineBreak(12) + "\nLeave a Team\n\n");

    getPrompt("Enter the Team ID of the team you would like to leave: ");
    teamName = getScanner().nextLine();

    String[] str = {teamName};
    return str;
  }


}
