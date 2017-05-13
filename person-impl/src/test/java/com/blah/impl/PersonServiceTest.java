package com.blah.impl;

import com.blah.api.PersonService;
import com.blah.api.SayingRequest;
import org.junit.Test;

import static com.lightbend.lagom.javadsl.testkit.ServiceTest.defaultSetup;
import static com.lightbend.lagom.javadsl.testkit.ServiceTest.withServer;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

public class PersonServiceTest {

    @Test
    public void shouldCausePersonToSayTheThingRequested() throws Exception {
        withServer(defaultSetup().withCassandra(true), server -> {
            PersonService service = server.client(PersonService.class);

            String msg1 = service
                    .askToSay("Bob")
                    .invoke(new SayingRequest("Hello there"))
                    .toCompletableFuture()
                    .get(5, SECONDS);

            assertEquals("Hello there from Bob", msg1);
        });
    }

}
