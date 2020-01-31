package sportsmate.players;

import org.junit.*;
import static org.junit.Assert.*;

// Write some Unit tests for your program like the following.

public class TestPlayer {

  public TestPlayer() {}

  @Test
  public void testGetFirstName() {

    Player student = new Player("John", "Doe");
    assertEquals("John", student.getFirstName());

  }


  @Test
  public void testSetFirstName() {
    Player student = new Player("John", "Doe");
    student.setFirstName("Bob");

    // age one year after is one
    assertEquals("Bob", student.getFirstName());
  }


  @Test
  public void testGetLastName() {

    Player student = new Player("John", "Doe");
    assertEquals("Doe", student.getLastName());

  }



  @Test
  public void testSetLastName() {

    Player student = new Player("John", "Doe");
    student.setLastName("Henrink");

    assertEquals("Henrink", student.getLastName());

  }


  @Test
  public void testPerson() {

    Player student = new Player("John", "Doe");
    assertEquals("John", student.getFirstName());
    assertEquals("Doe", student.getLastName());
  }

}
