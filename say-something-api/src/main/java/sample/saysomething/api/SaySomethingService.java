/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.saysomething.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

/**
 * The SaySomethingService service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the SaySomethingService.
 */
public interface SaySomethingService extends Service {

  /**
   * Example: curl -H "Content-Type: application/json" -X POST -d '{"message":
   * "Hi"}' http://localhost:9000/api/person/Alice/saying
   */
  ServiceCall<Message, String> say(String personId);

  @Override
  default Descriptor descriptor() {
    // @formatter:off
    return named("sayservice").with(
        pathCall("/api/person/:id/saying", this::say)
      ).withAutoAcl(true);
    // @formatter:on
  }
}
