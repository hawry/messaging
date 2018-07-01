package net.hawry.messaging.core.webhook;

import com.google.gson.annotations.SerializedName;

public class Event {
  @SerializedName("object") String object;
  @SerializedName("entry") Entry[] entry;

  public String getObject() {
    return this.object;
  }

  public Entry[] getEntries() {
    return this.entry;
  }
}