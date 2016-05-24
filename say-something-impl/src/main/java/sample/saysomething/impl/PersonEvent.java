/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.saysomething.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.Jsonable;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * This interface defines all the events that the PersonAggregateRoot entity supports.
 * <p>
 * By convention, the events should be inner classes of the interface, which
 * makes it simple to get a complete picture of what events an entity has.
 */
public interface PersonEvent extends Jsonable {

  /**
   * An event that represents a change in greeting lastSaid.
   */
  @SuppressWarnings("serial")
  @Immutable
  @JsonDeserialize
  final class Said implements PersonEvent {
    public final String message;

    @JsonCreator
    public Said(String message) {
      this.message = Preconditions.checkNotNull(message, "message");
    }

    @Override
    public boolean equals(@Nullable Object another) {
      if (this == another)
        return true;
      return another instanceof Said && equalTo((Said) another);
    }

    private boolean equalTo(Said another) {
      return message.equals(another.message);
    }

    @Override
    public int hashCode() {
      int h = 31;
      h = h * 17 + message.hashCode();
      return h;
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper("Said").add("message", message).toString();
    }
  }
}
