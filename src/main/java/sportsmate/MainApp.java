package sportsmate;

import sportsmate.menus.MainMenu;
import sportsmate.menus.MenuController;
import sportsmate.init.CreateDatabase;
import sportsmate.init.CreateTables;

public class MainApp {

  /**
   * Starts the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    new CreateDatabase();
    new CreateTables();
    MenuController mc = new MenuController();
    mc.setMenu(new MainMenu());
    mc.displayMenu();
  }
}


// Run from terminal
//=======================
// cd ~/vp/repos/sportsmate/target/classes
// java -cp .:sportsmate/ sportsmate.MainApp

// mvn exec:java -Dexec.mainClass=sportsmate.MainApp