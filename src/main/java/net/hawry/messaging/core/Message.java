package net.hawry.messaging.core;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import net.hawry.messaging.exceptions.MissingRequiredFieldException;

public class Message {
  @SerializedName("messaging_type") String messageType;
  @SerializedName("recipient") Participant recipient;
  @SerializedName("sender") Participant sender;
  @SerializedName("message") Content content;
  @SerializedName("sender_action") String senderAction; // this can not be used in conjunction with anything else than recipient
  @SerializedName("notification_type") String notification;
  @SerializedName("tag") String tag;

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

  /**
   * @param type optional push notification type of enum NotificationType
   */
  public void setNotificationType(NotificationType type) {
    this.notification = type.getValue();
  }

  /**
   * @return notification type value
   */
  public String getNotificationType() {
    return this.notification;
  }

  /**
   * If message type is MESSAGE_TAG, the tag must be set to a valid value.
   * @param type enum with the correct values
   */
  public void setMessageTag(MessageTagType type) {
    this.tag = type.getValue();
  }

  /**
   * @return message tag string
   */
  public String getMessageTag() {
    return this.tag;
  }

  /**
   * Sets the sender action to one of 'typing_on', 'typing_off' or 'mark_seen'. This can only be used in conjunction with recipient. All other fields must be null.
   */
  public void setSenderAction(SenderActionType type) {
    this.senderAction = type.getValue();
  }

  /**
   * @return the sender action payload
   */
  public String getSenderAction() {
    return this.senderAction;
  }

  /**
   * Serializes the object instance to a JSON string. If validation fails, an Exception will be thrown containing info on what fields that are missing and/or wrongly entered.
   * @return the json string representing the object
   */
  public String toJson() throws MissingRequiredFieldException {
    Gson g = new Gson();

    if (this.hasSenderAction()) {
      // strip all other fields except for recipient
      if (this.recipient==null)
        throw new MissingRequiredFieldException("recipient");

      try {
        this.recipient.validate();
      } catch (MissingRequiredFieldException ex) {
        throw new MissingRequiredFieldException(String.format("recipient: %s", ex.getMessage()));
      }

      return stripAndSerialize(g);
    }
   
    ArrayList<String> mF = new ArrayList<String>();
    
    // all other messages except for sender_action MUST have a messaging type
    if (this.messageType==null) {
      mF.add("messaging_type");
    } else {
      // check if message_tag
      if (this.messageType.equals(MessageType.MESSAGE_TAG.getValue())) {
        // check if tag is set
        if (this.tag==null)
          mF.add("tag");
      }
    }

    // and all other messages must also have a message
    if (this.content==null) {
      mF.add("content");
    } else {
      // if message exists, it must have either text or attachment set
      try {
        this.content.validate();
      } catch (MissingRequiredFieldException ex) {
        mF.add(String.format("content: %s", ex.getMessage()));
      }
    }

    // must be one recipient as well
    if (this.recipient==null) {
      mF.add("recipient");
    } else {
      try {
        this.recipient.validate();
      } catch (MissingRequiredFieldException ex) {
        mF.add(String.format("recipient: %s", ex.getMessage()));
      }
    }

    if (this.sender!=null) {
      // not a required field, but if it exists, it must be properly set
      try {
        this.sender.validate();
      } catch (MissingRequiredFieldException ex) {
        mF.add(String.format("sender: %s", ex.getMessage()));
      }
    }

    if (mF.size() > 0)
      throw new MissingRequiredFieldException(mF);

    return g.toJson(this);
  }

  private boolean hasSenderAction() {
    return this.senderAction!=null;
  }

  private String stripAndSerialize(Gson g) {
    this.content = null;
    this.notification = null;
    this.sender = null;
    this.tag = null;
    this.messageType = null;
    return g.toJson(this);
  }
}