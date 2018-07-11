package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.hawry.messaging.core.Content;
import net.hawry.messaging.core.Message;
import net.hawry.messaging.core.MessageBuilder;
import net.hawry.messaging.core.MessageTagType;
import net.hawry.messaging.core.MessageType;
import net.hawry.messaging.core.NotificationType;
import net.hawry.messaging.core.Participant;
import net.hawry.messaging.core.SenderActionType;
import net.hawry.messaging.core.send.MetadataMessage;
import net.hawry.messaging.exceptions.MissingRequiredFieldException;

@DisplayName("Message Builder")
class MessageBuilderTest {
  @Test
  @DisplayName("with regular content")
  void builder_Regular() {
    Participant p = new Participant("1233");
    
    MessageBuilder builder = new MessageBuilder();
    
    Message m = builder
      .setRecipient(p)
      .setContentText("hello, text")
      .setMessageType(MessageType.RESPONSE)
      .create();

    assertEquals("hello, text", m.getContent().getText());
    assertEquals("1233", m.getRecipient().getId());
    assertEquals("RESPONSE", m.getMessageType());
    
    try {
      m.toJson();
    } catch (MissingRequiredFieldException e) {
      Assertions.fail("exception thrown: " + e.getMessage());
    }
  }

  @Test
  @DisplayName("with metadata content")
  void builder_Metadata() {
    Participant p = new Participant("132");
    MessageBuilder builder = new MessageBuilder();

    Message m = builder
      .setRecipient(p)
      .setSender(p)
      .setMessageType(MessageType.MESSAGE_TAG)
      .setMessageTag(MessageTagType.TICKET_UPDATE)
      .setContentText("hello, text")
      .setContentMetadata("hello, metadata")
      .setNotificationType(NotificationType.NO_PUSH)
      .create();

    MetadataMessage meta = (MetadataMessage) m.getContent();

    assertEquals("hello, metadata", meta.getMetadata());
    assertEquals("NO_PUSH", m.getNotificationType());
  }

  @Test
  @DisplayName("metadata content with empty content first")
  void builder_Metadata_NullContent() {
    Participant p = new Participant("123");
    MessageBuilder builder = new MessageBuilder();

    Message m = builder
      .setContentMetadata("hello, metadata")
      .setContentText("hello, world")
      .setRecipient(p)
      .setMessageType(MessageType.RESPONSE)
      .create();

    try {
      m.toJson();
    } catch (MissingRequiredFieldException e) {
      Assertions.fail("exception thrown: " + e.getMessage());
    }
  }

  @Test
  @DisplayName("set sender action")
  void builder_SenderAction() {
    Participant p = new Participant("123");
    MessageBuilder builder = new MessageBuilder();
    Message m = builder
      .setRecipient(p)
      .setSenderAction(SenderActionType.MARK_SEEN)
      .create();

    assertEquals("mark_seen", m.getSenderAction());
  }

  @Test
  @DisplayName("set full content")
  void builder_Content() {
    Content c = new Content();
    c.setText("hello, content");
    Participant p = new Participant("123");
    MessageBuilder builder = new MessageBuilder();
    Message m = builder
      .setRecipient(p)
      .setMessageType(MessageType.RESPONSE)
      .setContent(c)
      .create();

    assertNotNull(m.getContent());

    try {
      m.toJson();
    } catch (MissingRequiredFieldException e) {
      Assertions.fail("exception thrown: " + e.getMessage());
    }
  }
}