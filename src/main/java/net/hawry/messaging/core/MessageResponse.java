package net.hawry.messaging.core;

import com.google.gson.annotations.SerializedName;

public class MessageResponse {
  @SerializedName("recipient_id") String recipientId;
  @SerializedName("message_id") String messageId;

  /**
   * @return recipient id
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

  /**
   * @return true if response contains both a message id and recipient id
   */
  public boolean isValid() {
    return this.recipientId.length() > 0 && this.messageId.length() > 0;
  }
}