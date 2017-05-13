/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.blah.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.AggregateEvent;
import com.lightbend.lagom.javadsl.persistence.AggregateEventShards;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTagger;
import com.lightbend.lagom.serialization.Jsonable;
import lombok.Value;

/**
 * This interface defines all the events that the {@link PersonEntity} supports.
 * <p>
 * By convention, the events should be inner classes of the interface, which
 * makes it simple to get a complete picture of what events an entity has.
 */
public interface PersonEvent extends Jsonable, AggregateEvent<PersonEvent> {

    /**
     * Tags are used for getting and publishing streams of events. Each event
     * will have this tag, and in this case, we are partitioning the tags into
     * 4 shards, which means we can have 4 concurrent processors/publishers of
     * events.
     */
    AggregateEventShards<PersonEvent> TAG = AggregateEventTag.sharded(PersonEvent.class, 4);

    /**
     * An event that represents a person saying something
     */
    @SuppressWarnings("serial")
    @Value
    @JsonDeserialize
    final class Said implements PersonEvent {
        private final String id;
        private final String saying;

        @JsonCreator
        public Said(@JsonProperty("id") String id, @JsonProperty("saying") String saying) {
            this.id = Preconditions.checkNotNull(id, "id");
            this.saying = Preconditions.checkNotNull(saying, "saying");
        }
    }

    @Override
    default AggregateEventTagger<PersonEvent> aggregateTag() {
        return TAG;
    }
}
