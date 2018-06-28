package net.hawry.messaging.core;

import com.google.gson.annotations.SerializedName;

public class MessageResponse {
  @SerializedName("recipient_id") String recipientId;
  @SerializedName("message_id") String messageId;
  @SerializedName("user_ref") String userRef;
  @SerializedName("phone_number") String phoneNumber;

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
   * @return true if recipient is identified by regular recipient id
   */
  public boolean hasRecipientId() {
    return (this.recipientId != null);
  }

  /**
   * @return true if recipient is identified by phone number
   */
  public boolean hasPhoneNumber() {
    return (this.phoneNumber != null);
  }

  /**
   * @return true if recipient is identified by user reference
   */
  public boolean hasUserRef() {
    return (this.userRef != null);
  }

  /**
   * @return user phone number if hasPhoneNumber returned true, otherwise null
   */
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * @return user reference number if hasUserRef returned true, otherwise null
   */
  public String getUserRef() {
    return this.userRef;
  }
}