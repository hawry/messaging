package net.hawry.messaging.core.webhook;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import net.hawry.messaging.exceptions.InvalidJsonException;

public class Event {
  @SerializedName("object") String object;
  @SerializedName("entry") Entry[] entry;

  public String getObject() {
    return this.object;
  }

  public Entry[] getEntries() {
    return this.entry;
  }

  public static Event fromJson(String json) throws InvalidJsonException {
    Gson g = new Gson();
    Event event = null;
    try {
      event = g.fromJson(json, Event.class);
    } catch (JsonSyntaxException e) {
      throw new InvalidJsonException(Event.class.getSimpleName());
    }
    return event;
  }
}