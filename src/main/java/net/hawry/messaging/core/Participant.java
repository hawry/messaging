package net.hawry.messaging.core;

import com.google.gson.annotations.SerializedName;

import net.hawry.messaging.exceptions.MissingRequiredFieldException;

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

  protected void validate() throws MissingRequiredFieldException {
    if (this.id==null)
      throw new MissingRequiredFieldException("id");

    if (this.id.length() == 0)
      throw new MissingRequiredFieldException("id");
  }
}