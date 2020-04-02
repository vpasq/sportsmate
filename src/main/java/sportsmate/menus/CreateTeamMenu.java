package sportsmate.menus;

public class CreateTeamMenu extends AbstractMenu {


  /**
   * Displays menu to create team.
   */
  @Override
  public String[] displayMenu() {
    String teamName;


    getPrompt("\n" + getLineBreak(27) + "\nCreate a new Team\n\n");

    getPrompt("Enter the name of the team: ");
    teamName = getScanner().nextLine();


    String[] str = {teamName};
    return str;
  }

}
