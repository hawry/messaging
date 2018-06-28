package net.hawry.messaging.core;

import com.google.gson.annotations.SerializedName;

public class MessageResponse {
  @SerializedName("recipient_id") String recipientId;
  @SerializedName("message_id") String messageId;

  /**
   * @return recipient id if hasRecipientId returns true, otherwise null
   */
  public String getRecipientId() {
    return this.recipientId;
  }

  /**
   * @return message id prefixed with 'mid.'
   */
  public String getMessageId() {
    return this.messageId;
  }
}