package sportsmate.menus;

public class JoinTeamMenu extends AbstractMenu {


  /**
   * Displays menu to join team.
   */
  @Override
  public String[] displayMenu() {
    String teamName;

    getPrompt("\n" + getLineBreak(11) + "\nJoin a Team\n\n");

    getPrompt("Enter the team id of the team you would like to join: ");
    teamName = getScanner().nextLine();

    String[] str = {teamName};
    return str;
  }

}

