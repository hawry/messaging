package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import net.hawry.messaging.core.Content;
import net.hawry.messaging.core.Message;
import net.hawry.messaging.core.MessageTagType;
import net.hawry.messaging.core.MessageType;
import net.hawry.messaging.core.NotificationType;
import net.hawry.messaging.core.Participant;
import net.hawry.messaging.core.SenderActionType;
import net.hawry.messaging.exceptions.MissingRequiredFieldException;

@DisplayName("Validations")
class ValidationTest {
  static Content con = null;
  static Participant par = null;

  @BeforeAll
  static void setUp() {
    con = new Content();
    con.setText("hello, world");

    par = new Participant("123");
  }

  @Nested
  @DisplayName("Messaging validation")
  class NestedMessagingValidation {


    @Test
    @DisplayName("completely empty message throws exception")
    void validation_Empty() {
      Message m = new Message();
      Throwable t = assertThrows(MissingRequiredFieldException.class, () -> { m.toJson(); });
      assertEquals("Missing required field(s): [messaging_type, content, recipient]", t.getMessage());
    }

    @Test
    @DisplayName("missing recipient")
    void validation_MissingRecipient() {
      Message m = new Message();
      m.setContent(con);
      m.setMessageType(MessageType.RESPONSE);
      
      Throwable t = assertThrows(MissingRequiredFieldException.class, () -> { m.toJson();});
      assertEquals("Missing required field(s): [recipient]", t.getMessage());
    }

    @Test
    @DisplayName("with empty recipient")
    void validation_EmptyRecipient() {
      Message m = new Message();
      m.setContent(con);
      m.setMessageType(MessageType.RESPONSE);
      m.setRecipient(new Participant(""));

      Throwable t = assertThrows(MissingRequiredFieldException.class, () -> { m.toJson(); });
      assertEquals("Missing required field(s): [recipient: Missing required field(s): id]", t.getMessage());
    }

    @Test
    @DisplayName("without message tag")
    void validation_WithoutMessageTag() {
      Message m = new Message();
      m.setContent(con);
      m.setRecipient(par);
      m.setMessageType(MessageType.MESSAGE_TAG);

      Throwable t = assertThrows(MissingRequiredFieldException.class, () -> { m.toJson(); });
      assertEquals("Missing required field(s): [tag]", t.getMessage());
    }

    @Test
    @DisplayName("with empty content text")
    void validation_EmptyContentText() {
      Message m = new Message();
      m.setRecipient(par);
      m.setMessageType(MessageType.RESPONSE);
      m.setContent(new Content());

      Throwable t = assertThrows(MissingRequiredFieldException.class, () -> { m.toJson();} );
      assertEquals("Missing required field(s): [content: Missing required field(s): text]", t.getMessage());
    }

    @Test
    @DisplayName("sender action without recipient and null recipient")
    void validation_SenderAction_NoRecipient() {
      Message m = new Message();
      m.setSenderAction(SenderActionType.MARK_SEEN);
      Throwable t = assertThrows(MissingRequiredFieldException.class, () -> { m.toJson();} );
      assertEquals("Missing required field(s): recipient", t.getMessage());

      m.setRecipient(new Participant(""));
      Throwable tt = assertThrows(MissingRequiredFieldException.class, () -> { m.toJson(); });
      assertEquals("Missing required field(s): recipient: Missing required field(s): id", tt.getMessage());
    }

    @Test
    @DisplayName("sender id missing")
    void validation_SenderMissing() {
      Message m = new Message();
      m.setContent(con);
      m.setRecipient(par);
      m.setMessageType(MessageType.RESPONSE);
      m.setSender(new Participant(""));

      Throwable t = assertThrows(MissingRequiredFieldException.class, () -> { m.toJson(); });
      assertEquals("Missing required field(s): [sender: Missing required field(s): id]", t.getMessage());
    }

    @Test
    @DisplayName("sender action strips everything else")
    void validation_SenderAction_Strip() {
      Message m = new Message();
      m.setContent(con);
      m.setMessageType(MessageType.MESSAGE_TAG);
      m.setRecipient(par);
      m.setSender(par);
      m.setNotificationType(NotificationType.NO_PUSH);
      m.setMessageTag(MessageTagType.TICKET_UPDATE);

      m.setSenderAction(SenderActionType.MARK_SEEN);
      try {
        m.toJson();   
      } catch (MissingRequiredFieldException e) {
        Assertions.fail("exception thrown: " + e.getMessage());
      }
      assertNull(m.getContent());
      assertNull(m.getNotificationType());
      assertNull(m.getMessageTag());
      assertNull(m.getMessageType());
      assertNull(m.getSender());
    }

    @Test
    @DisplayName("properly serializes a valid message")
    void validation_Success() {
      Message m = new Message();
      m.setContent(con);
      m.setRecipient(par);
      m.setMessageType(MessageType.RESPONSE);
      try {
        m.toJson();
      } catch (MissingRequiredFieldException e) {
        Assertions.fail("exception thrown: " + e.getMessage());
      }
    }
  }
}