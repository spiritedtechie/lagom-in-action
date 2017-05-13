/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.blah.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.base.Preconditions;
import lombok.Value;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PersonEvent.Said.class, name = "person-said")
})
public interface PersonEvent {

    @Value
    final class Said implements PersonEvent {
        public final String saying;
        private final String personId;

        @JsonCreator
        public Said(String id, String saying) {
            this.personId = id;
            this.saying = Preconditions.checkNotNull(saying, "saying");
        }
    }
}
