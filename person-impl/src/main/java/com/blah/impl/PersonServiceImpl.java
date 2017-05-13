/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.blah.impl;

import com.blah.api.SayingRequest;
import com.blah.api.PersonService;
import com.blah.impl.PersonCommand.Say;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;

import javax.inject.Inject;

/**
 * Implementation of the PersonService.
 */
public class PersonServiceImpl implements PersonService {

    private final PersistentEntityRegistry persistentEntityRegistry;

    @Inject
    public PersonServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
        this.persistentEntityRegistry = persistentEntityRegistry;
        persistentEntityRegistry.register(PersonEntity.class);
    }

    @Override
    public ServiceCall<SayingRequest, String> askToSay(String id) {
        return request -> {
            // Look up the person entity for the given ID.
            PersistentEntityRef<PersonCommand> ref = persistentEntityRegistry.refFor(PersonEntity.class, id);
            // Ask the entity the Say command.
            return ref.ask(new Say(request.getSaying()));
        };
    }
}
