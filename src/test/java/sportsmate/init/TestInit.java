package sportsmate.init;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestInit {

  @Test
  public void testCreateDatabase() {
    // setup
    CreateDatabase createDatabase = new CreateDatabase();
    String expected = "database created";

    // exercise
    String actual  = createDatabase.getDatabaseCreated();

    // verify
    assertEquals(expected, actual);

    // teardown
    createDatabase = null;
  }

  @Test
  public void testCreateTables() {
    // setup
    CreateTables createTables = new CreateTables();
    String expected = "user:player:personal_match:personal_match_players:";

    // exercise
    String actual  = createTables.getTablesCreated();

    // verify
    assertEquals(expected, actual);

    // teardown
    createTables = null;
  }

  @Test
  public void testDropTables() {
    // setup
    DropTables dropTables = new DropTables();
    String expected = "player_matches:player:user:";

    // exercise
    String actual  = dropTables.getTablesDropped();

    // verify
    assertEquals(expected, actual);

    // teardown
    dropTables = null;
  }

  @Test
  public void testDropDatabase() {
    // setup
    DropDatabase dropDatabase = new DropDatabase();
    String expected = "database dropped";

    // exercise
    String actual  = dropDatabase.getDatabaseDropped();

    // verify
    assertEquals(expected, actual);

    // teardown
    dropDatabase = null;
  }

}
