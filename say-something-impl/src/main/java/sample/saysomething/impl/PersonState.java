/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.saysomething.impl;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.CompressedJsonable;

/**
 * The state for the {@link PersonAggregateRoot} entity.
 */
@SuppressWarnings("serial")
@Immutable
@JsonDeserialize
public final class PersonState implements CompressedJsonable {

  public final String lastSaid;
  public final String timestamp;

  @JsonCreator
  public PersonState(String message, String timestamp) {
    this.lastSaid = Preconditions.checkNotNull(message, "lastSaid");
    this.timestamp = Preconditions.checkNotNull(timestamp, "timestamp");
  }

  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another)
      return true;
    return another instanceof PersonState && equalTo((PersonState) another);
  }

  private boolean equalTo(PersonState another) {
    return lastSaid.equals(another.lastSaid) && timestamp.equals(another.timestamp);
  }

  @Override
  public int hashCode() {
    int h = 31;
    h = h * 17 + lastSaid.hashCode();
    h = h * 17 + timestamp.hashCode();
    return h;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("PersonState").add("lastSaid", lastSaid).add("timestamp", timestamp).toString();
  }
}
