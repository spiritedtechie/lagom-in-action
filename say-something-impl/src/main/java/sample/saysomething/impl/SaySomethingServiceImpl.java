/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.saysomething.impl;

import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;

import javax.inject.Inject;
import sample.saysomething.api.Message;
import sample.saysomething.api.SaySomethingService;

/**
 * Implementation of the SaySomethingService.
 */
public class SaySomethingServiceImpl implements SaySomethingService {

  private final PersistentEntityRegistry persistentEntityRegistry;

  @Inject
  public SaySomethingServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
    this.persistentEntityRegistry = persistentEntityRegistry;
    persistentEntityRegistry.register(PersonAggregateRoot.class);
  }

  @Override
  public ServiceCall<Message, String> say(String personId) {
    return request -> {
      PersistentEntityRef<PersonCommand> ref = persistentEntityRegistry.refFor(PersonAggregateRoot.class, personId);
      return ref.ask(new PersonCommand.Say(request.message));
    };

  }

}
