package net.hawry.messaging.core.webhook;

import com.google.gson.annotations.SerializedName;

import net.hawry.messaging.core.Content;

public class WebhookMessage extends Content {
  @SerializedName("mid") String mid;

  /**
   * @return message id string
   */
  public String getMessageId() {
    return this.mid;
  }
}