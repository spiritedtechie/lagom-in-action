/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.blah.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.CompressedJsonable;
import lombok.Value;

/**
 * The state for the {@link PersonEntity} entity.
 */
@SuppressWarnings("serial")
@Value
@JsonDeserialize
public final class PersonState implements CompressedJsonable {

    public final String lastSaid;
    public final String timestamp;

    @JsonCreator
    public PersonState(@JsonProperty("lastSaid") String lastSaid, @JsonProperty("timestamp") String timestamp) {
        this.lastSaid = Preconditions.checkNotNull(lastSaid, "lastSaid");
        this.timestamp = Preconditions.checkNotNull(timestamp, "timestamp");
    }
}
