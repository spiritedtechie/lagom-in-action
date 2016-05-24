/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.saysomething.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import sample.saysomething.api.SaySomethingService;

/**
 * The module that binds the SaySomethingService so that it can be served.
 */
public class SaySomethingServiceModule extends AbstractModule implements ServiceGuiceSupport {
  @Override
  protected void configure() {
    bindServices(serviceBinding(SaySomethingService.class, SaySomethingServiceImpl.class));
  }
}
