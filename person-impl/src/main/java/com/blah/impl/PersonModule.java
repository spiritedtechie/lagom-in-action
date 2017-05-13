/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.blah.impl;

import com.blah.api.PersonService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

/**
 * The module that binds the PersonService so that it can be served.
 */
public class PersonModule extends AbstractModule implements ServiceGuiceSupport {
  @Override
  protected void configure() {
    bindService(PersonService.class, PersonServiceImpl.class);
  }
}
