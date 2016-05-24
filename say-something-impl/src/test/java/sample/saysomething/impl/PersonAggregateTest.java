package sample.saysomething.impl;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lightbend.lagom.javadsl.testkit.PersistentEntityTestDriver;
import com.lightbend.lagom.javadsl.testkit.PersistentEntityTestDriver.Outcome;

import akka.actor.ActorSystem;
import akka.testkit.JavaTestKit;

public class PersonAggregateTest {

  static ActorSystem system;

  @BeforeClass
  public static void setup() {
    system = ActorSystem.create("PersonAggregateTest");
  }

  @AfterClass
  public static void teardown() {
    JavaTestKit.shutdownActorSystem(system);
    system = null;
  }

  @Test
  public void testMessage() {
    PersistentEntityTestDriver<PersonCommand, PersonEvent, PersonState> driver = new PersistentEntityTestDriver<>(system,
        new PersonAggregateRoot(), "person-1");

    // one command
    Outcome<PersonEvent, PersonState> outcome1 = driver.run(new PersonCommand.Say("Hola"));
    assertEquals("Hola", outcome1.getReplies().get(0));
    assertEquals(Collections.emptyList(), outcome1.issues());

    // two commands
    Outcome<PersonEvent, PersonState> outcome2 = driver.run(new PersonCommand.Say("Hi"), new PersonCommand.Say("Howdy"));

    assertEquals(1, outcome2.events().size());
    assertEquals(new PersonEvent.Said("Hi"), outcome2.events().get(0));
    assertEquals(new PersonEvent.Said("Howdy"), outcome2.events().get(1));
    assertEquals("Howdy", outcome2.state().lastSaid);

    assertEquals("Hi", outcome2.getReplies().get(0));
    assertEquals("Howdy", outcome2.getReplies().get(1));
    assertEquals(2, outcome2.getReplies().size());

    assertEquals(Collections.emptyList(), outcome2.issues());
  }

}
