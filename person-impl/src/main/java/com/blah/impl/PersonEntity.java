/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.blah.impl;

import com.blah.impl.PersonCommand.Say;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * This is an event sourced entity. It has a state, {@link PersonState}, which
 * stores what the saying should be (eg, "Hello").
 * <p>
 * Event sourced entities are interacted with by sending them commands. This
 * entity supports one commands, a {@link Say} command, which is
 * used to change the person said state.
 * <p>
 * Commands get translated to events, and it's the events that get persisted by
 * the entity. Each event will have an event handler registered for it, and an
 * event handler simply applies an event to the current state. This will be done
 * when the event is first created, and it will also be done when the entity is
 * loaded from the database - each event will be replayed to recreate the state
 * of the entity.
 * <p>
 * This entity defines one event, the {@link PersonEvent.Said} event,
 * which is emitted when a {@link PersonCommand} command is received.
 */
public class PersonEntity extends PersistentEntity<PersonCommand, PersonEvent, PersonState> {

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
     * Otherwise, the default state is to use the Hello saying.
     */
        BehaviorBuilder b = newBehaviorBuilder(
                snapshotState.orElse(new PersonState("Hello", LocalDateTime.now().toString())));
    /*
     * Command handler for the Say command.
     */
        b.setCommandHandler(Say.class, (cmd, ctx) ->
                // In response to this command, we want to first persist it as a PersonEvent.Said event
                ctx.thenPersist(new PersonEvent.Said(entityId(), cmd.getSaying()),
                        // Then once the event is successfully persisted, we respond with done.
                        evt -> ctx.reply(cmd.getSaying() + " from " + this.entityId())));

    /*
     * Event handler for the Said event.
     */
        b.setEventHandler(PersonEvent.Said.class,
                // We simply update the current state to use the saying from
                // the event.
                evt -> new PersonState(evt.getSaying(), LocalDateTime.now().toString()));

    /*
     * We've defined all our behaviour, so build and return it.
     */
        return b.build();
    }

}
