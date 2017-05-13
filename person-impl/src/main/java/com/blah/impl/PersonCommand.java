/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.blah.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.CompressedJsonable;
import com.lightbend.lagom.serialization.Jsonable;
import lombok.Value;

/**
 * This interface defines all the commands that the PersonEntity supports.
 * <p>
 * By convention, the commands should be inner classes of the interface, which
 * makes it simple to get a complete picture of what commands an entity
 * supports.
 */
public interface PersonCommand extends Jsonable {

    /**
     * A command to for the person entity to say something
     * <p>
     * It has a reply type of {{@link String}}, which is sent back to the caller
     * when all the events emitted by this command are successfully persisted.
     */
    @SuppressWarnings("serial")
    @Value
    @JsonDeserialize
    final class Say implements PersonCommand, CompressedJsonable, PersistentEntity.ReplyType<String> {
        private final String saying;

        @JsonCreator
        public Say(@JsonProperty("saying") String saying) {
            this.saying = Preconditions.checkNotNull(saying, "saying");
        }
    }

}
