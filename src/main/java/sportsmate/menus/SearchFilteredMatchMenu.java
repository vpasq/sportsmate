package sportsmate.menus;

public class SearchFilteredMatchMenu extends AbstractMenu {

    @Override
    String[] displayMenu() {
        Boolean Flag = false;
        String matchID;
        String location;
        String date;
        String gameType;
        String playersLeft;
        getPrompt("\n*Press Enter to skip\n");
        getPrompt("Enter the Match ID: ");
        matchID = getScanner().nextLine();
        getPrompt("Enter the location: ");
        location = getScanner().nextLine();
        getPrompt("Enter the date（mm-dd-yyyy）: ");
        date = getScanner().nextLine();
        getPrompt("Enter the game type: ");
        gameType = getScanner().nextLine();
        getPrompt("Enter the number of the positions left: ");
        playersLeft = getScanner().nextLine();
        String[] str = {matchID, location, date, gameType, playersLeft};
        return str;
    }
}
