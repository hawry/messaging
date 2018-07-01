package net.hawry.messaging.core.webhook;

import com.google.gson.annotations.SerializedName;

public class Entry {
  @SerializedName("id") String id;
  @SerializedName("time") long time;
  @SerializedName("messaging") Messaging[] messaging;

  /**
   * @return page id of page
   */
  public String getId() {
    return this.id;
  }

  /**
   * @return time of update (epoch in ms)
   */
  public long getTime() {
    return this.time;
  }

  /**
   * @return message if exists, null otherwise
   */
  public Messaging getMessaging() {
    if (this.messaging == null)
      return null;

    if (!(this.messaging.length>0))
      return null;
    
    return this.messaging[0]; // there should ever only be one messaging object here according to the spec
  }
}