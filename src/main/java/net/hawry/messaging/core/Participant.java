package net.hawry.messaging.core;

import com.google.gson.annotations.SerializedName;

public class Participant {
  @SerializedName("id") String id;

  public Participant(String id) {
    this.id = id;
  }

  /**
   * @return the participant id
   */
  public String getId() {
    return id;
  }
}