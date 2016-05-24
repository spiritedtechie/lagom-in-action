/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.saysomething.impl;

import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import sample.saysomething.impl.PersonEvent.Said;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * This is an event sourced entity. It has a state, {@link PersonState}, which
 * stores what the last said message was (eg, "hello").
 * <p>
 * Event sourced entities are interacted with by sending them commands. This
 * entity supports the commands, a {@link PersonCommand.Say} command, which is
 * used to command the person to say something.
 * <p>
 * Commands get translated to events, and it's the events that get persisted by
 * the entity. Each event will have an event handler registered for it, and an
 * event handler simply applies an event to the current state. This will be done
 * when the event is first created, and it will also be done when the entity is
 * loaded from the database - each event will be replayed to recreate the state
 * of the entity.
 * <p>
 * This entity defines one event, the {@link Said} event,
 * which is emitted when a {@link PersonCommand.Say} command is received.
 */
public class PersonAggregateRoot extends PersistentEntity<PersonCommand, PersonEvent, PersonState> {

  /**
   * An entity can define different behaviours for different states, but it will
   * always start with an initial behaviour. This entity only has one behaviour.
   */
  @Override
  public Behavior initialBehavior(Optional<PersonState> snapshotState) {

    /*
     * Behaviour is defined using a behaviour builder. The behaviour builder
     * starts with a state, if this entity supports snapshotting (an
     * optimisation that allows the state itself to be persisted to combine many
     * events into one), then the passed in snapshotState may have a value that
     * can be used.
     *
     * Otherwise, the default state is the person said "Hello"
     */
    BehaviorBuilder b = newBehaviorBuilder(
        snapshotState.orElse(new PersonState("Hello", LocalDateTime.now().toString())));

    /*
     * Command handler for the Say command.
     */
    b.setCommandHandler(PersonCommand.Say.class, (cmd, ctx) ->
    // In response to this command, we want to first persist it as a
    // Said event
      ctx.thenPersist(new PersonEvent.Said(cmd.message),
          // Then once the event is successfully persisted, we respond with done.
          evt -> ctx.reply(cmd.message))
    );

    /*
     * Event handler for the Said event.
     */
    b.setEventHandler(Said.class,
        // We simply update the current state to use the greeting lastSaid from
        // the event.
        evt -> new PersonState(evt.message, LocalDateTime.now().toString()));

    /*
     * We've defined all our behaviour, so build and return it.
     */
    return b.build();
  }

}
