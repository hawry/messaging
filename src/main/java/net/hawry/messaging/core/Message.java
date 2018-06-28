package net.hawry.messaging.core;

import com.google.gson.annotations.SerializedName;

public class Message {
  @SerializedName("messaging_type") String messageType;
  @SerializedName("recipient") Participant recipient;
  @SerializedName("sender") Participant sender;
  @SerializedName("message") Content content;

  /**
   * Sets the payload message type. One of 'response', 'update' or 'message_tag' with a valid tag
   * 
   * @param type Instance of the MessageType enum
   */
  public void setMessageType(MessageType type) {
    this.messageType = type.getValue();
  }

  /**
   * @return the message type, should be one of 'response', 'update' or 'message_tag'
   */
  public String getMessageType() {
    return this.messageType;
  }

  /**
   * @param p object representing the recipient of the message
   */
  public void setRecipient(Participant p) {
    this.recipient = p;
  }

  /**
   * @param p object representing the sender of the message
   */
  public void setSender(Participant p) {
    this.sender = p;
  }

  /**
   * @return object representing the recipient
   */
  public Participant getRecipient() {
    return this.recipient;
  }

  /**
   * @return object representing the sender
   */
  public Participant getSender() {
    return this.sender;
  }

  /**
   * @param content sets the message body to content
   */
  public void setContent(Content content) {
    this.content = content;
  }

  /**
   * @return the message content
   */
  public Content getContent() {
    return this.content;
  }
}