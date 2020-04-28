package sportsmate.menus;

import sportsmate.dao.LoginDAO;
import sportsmate.dao.PersonalMatchDAO;
import sportsmate.dao.PlayerDAO;
import sportsmate.dao.TeamDAO;

import java.text.ParseException;
import sportsmate.dao.TeamMatchDAO;

public class MenuController {
  private AbstractMenu menu;
  private int loggedInUserID;

  /**
   * Sets the menu reference type.
   *
   * @param menu the menu reference type
   */
  public void setMenu(AbstractMenu menu) {
    this.menu = menu;
  }

  /**
   * Delegate to the subtype menus
   */
  public void displayMenu() {
    checkMenuSelection(menu.displayMenu());
  }

  /**
   * Checks user menu selection.
   *
   * @param selectionArr the user input array
   */
  private void checkMenuSelection(String[] selectionArr) {

    if (menu instanceof MainMenu) {
      switch (selectionArr[0]) {
        case "1":
          setMenu(new LogInMenu());
          displayMenu();
          break;
        case "2":
          setMenu(new RegisterMenu());
          displayMenu();
          break;
        case "3":
          System.out.println("\nExiting...\n");
          System.exit(1);
          break;
        default:
          break;
      }
    }

    else if (menu instanceof RegisterMenu) {
      String userFirstName = selectionArr[0];
      String userLastName = selectionArr[1];
      String username = selectionArr[2];
      String userGender = selectionArr[3];
      String password = selectionArr[4];
      PlayerDAO playerDao = new PlayerDAO();
      playerDao.createPlayer(userFirstName, userLastName, userGender, username, password);
      setMenu(new LoginOrExitMenu());
      displayMenu();
    }

    else if (menu instanceof LogInMenu) {
      String username = selectionArr[0];
      String password = selectionArr[1];
      LoginDAO loginDao = new LoginDAO();
      boolean userExists = loginDao.authLogin(username, password);
      if (userExists) {
        menu.getPrompt("\n" + menu.getLineBreak(username.length(), 39) + "\nHello " + username + ", "
            + "you have succesfully logged in!\n");
        loggedInUserID = loginDao.getLoggedInUserID();
        setMenu(new MainSubMenu());
        displayMenu();
      } else {
        menu.getPrompt("\n" + menu.getLineBreak(40) + "\nUsername and/or Password Does Not Exist!\n");
        System.out.println("\nExiting...\n");
        System.exit(1);
      }
    }

    // added by vp
    else if (menu instanceof MainSubMenu) {
      switch (selectionArr[0]) {
        case "1":
          System.out.println("\n================================");
          System.out.println("Create or Join a Personal Match:");
          setMenu(new PersonalMatchMenu());
          displayMenu();
          break;
        case "2":
          System.out.println("\n=====================");
          System.out.println("Create or Join a Team:");
          setMenu(new TeamMenu());
          displayMenu();
          break;
        case "3":
          System.out.println("\n============================");
          System.out.println("Create or Join a Team Match:");
          setMenu(new TeamMatchMenu());
          displayMenu();
          break;
//        case "4":
//          setMenu(new SearchMenu());
//          displayMenu();
//          break;
        case "4":
          System.out.println("\nExiting...\n");
          System.exit(1);
          break;
        default:
          break;
        }
    }

    else if (menu instanceof LoginOrExitMenu) {
      switch (selectionArr[0]) {
        case "1":
          setMenu(new LogInMenu());
          displayMenu();
          break;
        case "2":
          System.out.println("\nExiting...\n");
          System.exit(1);
          break;
        default:
          break;
      }
    }

    else if (menu instanceof PersonalMatchMenu) {
      PersonalMatchDAO personalMatchDAO = new PersonalMatchDAO();
      switch (selectionArr[0]) {
        case "1":
          setMenu(new CreatePersonalMatchMenu());
          displayMenu();
          break;
        case "2":
          setMenu(new JoinPersonalMatchMenu());
          displayMenu();
          break;
        case "3":
          personalMatchDAO.listPersonalMatchesCreated(loggedInUserID);
          setMenu(new PersonalMatchMenu());
          displayMenu();
          break;
        case "4":
          personalMatchDAO.listPersonalMatchesJoined(loggedInUserID);
          setMenu(new PersonalMatchMenu());
          displayMenu();
          break;
        case "5":
          personalMatchDAO.listAllPersonalMatches();
          setMenu(new PersonalMatchMenu());
          displayMenu();
          break;
        case "6":
          setMenu(new MainSubMenu());
          displayMenu();
          break;
        case "7":
          System.out.println("\nExiting...\n");
          System.exit(1);
          break;
        default:
          break;
      }
    }

    else if (menu instanceof CreatePersonalMatchMenu) {
      int gym_id = Integer.parseInt(selectionArr[0]);
      String game_date = selectionArr[1];
      String startAt = selectionArr[2];
      String endAt = selectionArr[3];
      String game_type = selectionArr[4];
      int num_initial_players = Integer.parseInt(selectionArr[5]);
      PersonalMatchDAO personalMatchDAO = new PersonalMatchDAO();
      personalMatchDAO.createPersonalMatch(loggedInUserID, gym_id, game_date, startAt, endAt,
          game_type, num_initial_players);
      setMenu(new PersonalMatchMenu());
      displayMenu();
    }

    else if (menu instanceof JoinPersonalMatchMenu) {
      //int match_id = Integer.parseInt(selectionArr[0]);
      PersonalMatchDAO personalMatchDAO = new PersonalMatchDAO();
      personalMatchDAO.joinPersonalMatch(loggedInUserID);
      setMenu(new PersonalMatchMenu());
      displayMenu();
    }

    else if (menu instanceof SearchMenu) {
      switch (selectionArr[0]) {
        case "1":
          PersonalMatchDAO personalMatchDAO = new PersonalMatchDAO();
          personalMatchDAO.listAllPersonalMatches();
          setMenu(new SearchMenu());
          displayMenu();
          break;
        case "2":
          TeamDAO teamDAO = new TeamDAO();
          teamDAO.listAllTeams();
          setMenu(new SearchMenu());
          displayMenu();
          break;
        case "3":
          TeamMatchDAO teamMatchDAO = new TeamMatchDAO();
          teamMatchDAO.listAllTeamMatches();
          setMenu(new SearchMenu());
          displayMenu();
        case "4":
          setMenu(new SearchFilteredMatchMenu());
          displayMenu();
          break;
        case "5":
          setMenu(new MainSubMenu());
          displayMenu();
          break;
        case "6":
          System.out.println("\nExiting...\n");
          System.exit(1);
          break;
        default:
          break;
      }
    }


    /*
    Function: listFilteredPersonalMatches
    Editor: Xiongbo Hu
    Date: 20200327
    */
    else if (menu instanceof SearchFilteredMatchMenu) {
      String matchID = selectionArr[0];
      int gym_id = Integer.parseInt(selectionArr[1]);
      String date = selectionArr[2];
      String gameType = selectionArr[3];
      String playersLeft = selectionArr[4];
      //int num_initial_players = Integer.parseInt(selectionArr[5]);
      PersonalMatchDAO personalMatchDAO = new PersonalMatchDAO();
      try {
        personalMatchDAO.listFilteredPersonalMatches(loggedInUserID, matchID, gym_id, date, gameType,
                playersLeft);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      setMenu(new SearchMenu());
      displayMenu();
    }

    else if (menu instanceof TeamMenu) {
      TeamDAO teamDAO;
      switch (selectionArr[0]) {
        case "1":
          setMenu(new CreateTeamMenu());
          displayMenu();
          break;
        case "2":
          setMenu(new JoinTeamMenu());
          displayMenu();
          break;
        case "3":
          setMenu(new LeaveTeamMenu());
          displayMenu();
          break;
        case "4":
          teamDAO = new TeamDAO();
          teamDAO.listTeamsCreated(loggedInUserID);
          setMenu(new TeamMenu());
          displayMenu();
          break;
        case "5":
          teamDAO = new TeamDAO();
          teamDAO.listTeamsJoined(loggedInUserID);
          setMenu(new TeamMenu());
          displayMenu();
          break;
        case "6":
          teamDAO = new TeamDAO();
          teamDAO.listAllTeams();
          setMenu(new TeamMenu());
          displayMenu();
          break;
        case "7":
          setMenu(new MainSubMenu());
          displayMenu();
          break;
        case "8":
          System.out.println("\nExiting...\n");
          System.exit(1);
          break;
        default:
          break;
      }
    }

    else if (menu instanceof CreateTeamMenu) {
      String teamName = selectionArr[0];
      TeamDAO teamDAO = new TeamDAO();
      teamDAO.createTeam(teamName, loggedInUserID);
      setMenu(new TeamMenu());
      displayMenu();
    }

    else if (menu instanceof JoinTeamMenu) {
      TeamDAO teamDAO = new TeamDAO();
      teamDAO.joinTeam(loggedInUserID);
      setMenu(new TeamMenu());

      displayMenu();
    }

    else if (menu instanceof LeaveTeamMenu) {
      int t_id = Integer.parseInt(selectionArr[0]);
      int tmplayer_id = loggedInUserID;
      TeamDAO teamDAO = new TeamDAO();
      teamDAO.leaveTeam(tmplayer_id, t_id);
      setMenu(new TeamMenu());
      displayMenu();
    }

    else if (menu instanceof TeamMatchMenu) {
      TeamMatchDAO teamMatchDAO;
      TeamDAO teamDAO;
      switch (selectionArr[0]) {
        case "1":
          teamDAO = new TeamDAO();
          teamDAO.listTeamsCreated(loggedInUserID);
          setMenu(new CreateTeamMatchMenu());
          displayMenu();
          break;
        case "2":
          teamDAO = new TeamDAO();
          teamDAO.listTeamsCreated(loggedInUserID);
          setMenu(new JoinTeamMatchMenu());
          displayMenu();
          break;
//        case "3":
//          System.out.println("\nExiting...\n");
//          System.exit(1);
//          setMenu(new CancelTeamMatchMenu());
//          displayMenu();
//          break;
        case "3":
          teamMatchDAO = new TeamMatchDAO();
          teamMatchDAO.listTeamMatchesCreated(loggedInUserID);
          setMenu(new TeamMatchMenu());
          displayMenu();
          break;
        case "4":
          teamMatchDAO = new TeamMatchDAO();
          teamMatchDAO.listTeamMatchesMyTeamJoined(loggedInUserID);
          setMenu(new TeamMatchMenu());
          displayMenu();
          break;
        case "5":
          teamMatchDAO = new TeamMatchDAO();
          teamMatchDAO.listAllTeamMatches();
          setMenu(new TeamMatchMenu());
          displayMenu();
          break;
        case "6":
          setMenu(new MainSubMenu());
          displayMenu();
          break;
        case "7":
          System.out.println("\nExiting...\n");
          System.exit(1);
          break;
        default:
          break;
      }
    }

    else if (menu instanceof CreateTeamMatchMenu) {
      int hostTeamID = Integer.parseInt(selectionArr[0]);
      int gymID = Integer.parseInt(selectionArr[0]);
      //String location = selectionArr[2];
      String game_date = selectionArr[3];
      String start_date = selectionArr[4];
      String end_date = selectionArr[5];
      TeamMatchDAO teamMatchDAO = new TeamMatchDAO();
      teamMatchDAO.createTeamMatch(loggedInUserID, hostTeamID,
          gymID, game_date, start_date, end_date);
      setMenu(new TeamMatchMenu());
      displayMenu();
    }


//    else if (menu instanceof CreatePersonalMatchMenu) {
//      int gym_id = Integer.parseInt(selectionArr[0]);
//      String game_date = selectionArr[1];
//      String startAt = selectionArr[2];
//      String endAt = selectionArr[3];
//      String game_type = selectionArr[4];
//      int num_initial_players = Integer.parseInt(selectionArr[5]);
//      PersonalMatchDAO personalMatchDAO = new PersonalMatchDAO();
//      personalMatchDAO.createPersonalMatch(loggedInUserID, gym_id, game_date, startAt, endAt,
//          game_type, num_initial_players);
//      setMenu(new PersonalMatchMenu());
//      displayMenu();
//    }


    else if (menu instanceof JoinTeamMatchMenu) {
      int guestTeamID = Integer.parseInt(selectionArr[0]);
      int teamMatchID = Integer.parseInt(selectionArr[1]);
      TeamMatchDAO teamMatchDAO = new TeamMatchDAO();
      teamMatchDAO.joinTeamMatch(loggedInUserID, guestTeamID, teamMatchID);
      setMenu(new TeamMatchMenu());
      displayMenu();
    }
  }

  public int getLoggedInUserID() {
    return loggedInUserID;
  }
}
