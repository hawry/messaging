package net.hawry.messaging.core;

import net.hawry.messaging.core.send.MetadataMessage;

public class MessageBuilder {
  MessageType messageType;
  SenderActionType senderAction;
  NotificationType notificationType;
  MessageTagType messageTag;
  Participant recipient, sender;
  Content content;

  private Message current;

  public MessageBuilder() {
    this.current = new Message();
  }

  public MessageBuilder setMessageType(MessageType type) {
    current.setMessageType(type);
    return this;
  }

  public MessageBuilder setSenderAction(SenderActionType type) {
    current.setSenderAction(type);
    return this;
  }

  public MessageBuilder setNotificationType(NotificationType type) {
    current.setNotificationType(type);
    return this;
  }

  public MessageBuilder setMessageTag(MessageTagType type) {
    current.setMessageTag(type);
    return this;
  }

  public MessageBuilder setRecipient(Participant p) {
    current.setRecipient(p);
    return this;
  }

  public MessageBuilder setSender(Participant p) {
    current.setSender(p);
    return this;
  }

  public MessageBuilder setContent(Content c) {
    current.setContent(c);
    return this;
  }

  public MessageBuilder setContentText(String text) {
    Content c = current.getContent();

    // if empty, set a default content here
    if (c == null) {
      c = new Content();
      c.setText(text);
      current.setContent(c);
      return this;
    }
    c.setText(text); // should work regardless of which subclass
    return this;
  }

  public MessageBuilder setContentMetadata(String metadata) {
    Content c = current.getContent();
    
    MetadataMessage m = new MetadataMessage();
    m.setMetadata(metadata);

    if (c == null) {
      // no worries, just push the metadata message in place, since the text should be empty
      current.setContent(m);
      return this;
    }

    // clone the old value
    m.setText(c.getText());
    current.setContent(m); // replace the old message with our new MetadataMessage
    return this;
  }

  public Message create() {
    return current;
  }
}