package sample.saysomething.api;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

@Immutable
@JsonDeserialize
public final class Message {

  public final String message;

  @JsonCreator
  public Message(String message) {
    this.message = Preconditions.checkNotNull(message, "message");
  }

  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another)
      return true;
    return another instanceof Message && equalTo((Message) another);
  }

  private boolean equalTo(Message another) {
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
    return MoreObjects.toStringHelper("Message").add("message", message).toString();
  }
}
