package sportsmate;

import sportsmate.auth.MainMenu;
import sportsmate.auth.MenuController;

public class MainApp {

  /**
   * Starts the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
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