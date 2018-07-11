package net.hawry.messaging.core.response;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import net.hawry.messaging.exceptions.InvalidJsonException;

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

  /**
   * @param json String representation of the JSON-object to serialize to a MessageResponse
   * @return A serialized MessageResponse
   */
  public static MessageResponse fromJson(String json) throws InvalidJsonException {
    Gson g = new Gson();
    MessageResponse r = null;
    try {
      r = g.fromJson(json, MessageResponse.class);
    } catch (JsonSyntaxException e) {
      throw new InvalidJsonException(MessageResponse.class.getSimpleName());
    }
    return r;
  }
}