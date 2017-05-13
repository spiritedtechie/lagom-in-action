package com.blah.api;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.*;

/**
 * The PersonService service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the PersonService.
 */
public interface PersonService extends Service {

    /**
     * Example: curl -H "Content-Type: application/json" -X POST -d '{"toSay":
     * "Hi"}' http://localhost:9000/api/person/Alice/saying
     */
    ServiceCall<SayingRequest, String> askToSay(String personId);

    @Override
    default Descriptor descriptor() {
        // @formatter:off
        return named("personservice")
                .withCalls(
                        pathCall("/api/person/:id/saying", this::askToSay)
                ).withAutoAcl(true);
        // @formatter:on
    }
}
