package net.hawry.messaging.core.response;

import com.google.gson.annotations.SerializedName;

public class MessageResponse {
  @SerializedName("recipient_id") String recipientId;
  @SerializedName("message_id") String messageId;
  @SerializedName("error") ResponseError error;

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

  /**
   * @return true if any errors occurred and the message that the response corresponds to failed
   */
  public boolean hasErrors() {
    return this.error != null;
  }

  /**
   * @return error object if hasErrors is true, otherwise null
   */
  public ResponseError getError() {
    return this.error;
  }
}