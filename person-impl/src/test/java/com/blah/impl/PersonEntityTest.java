package com.blah.impl;

import akka.actor.ActorSystem;
import akka.testkit.JavaTestKit;
import com.lightbend.lagom.javadsl.testkit.PersistentEntityTestDriver;
import com.lightbend.lagom.javadsl.testkit.PersistentEntityTestDriver.Outcome;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class PersonEntityTest {

    static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create("PersonEntityTest");
    }

    @AfterClass
    public static void teardown() {
        JavaTestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testPersonEntity() {
        PersistentEntityTestDriver<PersonCommand, PersonEvent, PersonState> driver = new PersistentEntityTestDriver<PersonCommand, PersonEvent, PersonState>(
                system, new PersonEntity(), "person-1");

        Outcome<PersonEvent, PersonState> outcome1 = driver.run(new PersonCommand.Say("Hello!"));
        assertEquals(1, outcome1.getReplies().size());
        assertEquals("Hello! from person-1", outcome1.getReplies().get(0));
        assertEquals("Hello!", outcome1.state().lastSaid);
        assertEquals(Collections.emptyList(), outcome1.issues());
    }

}
