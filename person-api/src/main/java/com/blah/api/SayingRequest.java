package com.blah.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import lombok.Value;

@Value
@JsonDeserialize
public final class SayingRequest {

    private final String saying;

    @JsonCreator
    public SayingRequest(@JsonProperty("saying") String saying) {
        this.saying = Preconditions.checkNotNull(saying, "saying");
    }
}
