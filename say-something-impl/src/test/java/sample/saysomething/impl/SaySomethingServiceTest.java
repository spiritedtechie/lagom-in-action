/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.saysomething.impl;

import org.junit.Test;
import sample.saysomething.api.Message;
import sample.saysomething.api.SaySomethingService;

import static com.lightbend.lagom.javadsl.testkit.ServiceTest.defaultSetup;
import static com.lightbend.lagom.javadsl.testkit.ServiceTest.withServer;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

public class SaySomethingServiceTest {

  @Test
  public void shouldStorePersonalizedGreeting() throws Exception {
    withServer(defaultSetup(), server -> {
      SaySomethingService service = server.client(SaySomethingService.class);

      String msg1 = service.say("Alice").invoke(new Message("Hi")).toCompletableFuture().get(5, SECONDS);
      assertEquals("Hi", msg1); // default greeting

      String msg2 = service.say("Alice").invoke(new Message("Howdy")).toCompletableFuture().get(5, SECONDS);
      assertEquals("Howdy", msg2); // default greeting
    });
  }

}
