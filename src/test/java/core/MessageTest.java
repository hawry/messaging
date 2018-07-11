package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.Stream;

import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import net.hawry.messaging.core.Content;
import net.hawry.messaging.core.Message;
import net.hawry.messaging.core.MessageTagType;
import net.hawry.messaging.core.MessageType;
import net.hawry.messaging.core.NotificationType;
import net.hawry.messaging.core.Participant;
import net.hawry.messaging.core.SenderActionType;

@DisplayName("Message")
class MessageTest {
  static Gson gson = null;
  static Message msg = null;
  @BeforeAll
  static void setUp() {
    gson = new Gson();
    msg = new Message();
  }

  @Nested
  @DisplayName("message types")
  class NestedMessageType {
    @Test
    @DisplayName("sets message MESSAGE_TAG")
    void setMessageType_Tag() {
      msg.setMessageType(MessageType.MESSAGE_TAG);
      assertEquals("MESSAGE_TAG", msg.getMessageType());
    }

    @Test
    @DisplayName("sets message type RESPONSE")
    void setMessageType_Response() {
      msg.setMessageType(MessageType.RESPONSE);
      assertEquals("RESPONSE", msg.getMessageType());
    }

    @Test
    @DisplayName("sets message type UPDATE")
    void setMessageType_Update() {
      msg.setMessageType(MessageType.UPDATE);
      assertEquals("UPDATE", msg.getMessageType());
    }
  }

  @Nested
  @DisplayName("notification types")
  class NestedNotificationType {
    @Test
    @DisplayName("sets notification REGULAR")
    void setNotificationType_Regular() {
      msg.setNotificationType(NotificationType.REGULAR);
      assertEquals("REGULAR", msg.getNotificationType());
    }

    @Test
    @DisplayName("sets notification SILENT_PUSH")
    void setNotificationType_SilentPush() {
      msg.setNotificationType(NotificationType.SILENT_PUSH);
      assertEquals("SILENT_PUSH", msg.getNotificationType());
    }

    @Test
    @DisplayName("sets notification NO_PUSH")
    void setNotificationType_NoPush() {
      msg.setNotificationType(NotificationType.NO_PUSH);
      assertEquals("NO_PUSH", msg.getNotificationType());
    }
  }

  @DisplayName("message tag type values")
  @ParameterizedTest(name = "for {0}")
  @MethodSource("tagProvider")
  void setMessageTag_Test(MessageTagType en, String expected) {
    msg.setMessageTag(en);
    assertEquals(expected, msg.getMessageTag());
  }

  static Stream<Arguments> tagProvider() {
    return Stream.of(
      Arguments.of(MessageTagType.ALERT, "COMMUNITY_ALERT"),
      Arguments.of(MessageTagType.EVENT_REMINDER, "CONFIRMED_EVENT_REMINDER"),
      Arguments.of(MessageTagType.NON_PROMO_SUBSCRIPTION, "NON_PROMOTIONAL_SUBSCRIPTION"),
      Arguments.of(MessageTagType.PAIRING_UPDATE, "PAIRING_UPDATE"),
      Arguments.of(MessageTagType.APP_UPDATE, "APPLICATION_UPDATE"),
      Arguments.of(MessageTagType.PAYMENT_UPDATE,"PAYMENT_UPDATE"),
      Arguments.of(MessageTagType.FINANCE_UPDATE, "PERSONAL_FINANCE_UPDATE"),
      Arguments.of(MessageTagType.SHIPPING_UPDATE, "SHIPPING_UPDATE"),
      Arguments.of(MessageTagType.RESERVATION_UPDATE, "RESERVATION_UPDATE"),
      Arguments.of(MessageTagType.ISSUE_RESOLUTION, "ISSUE_RESOLUTION"),
      Arguments.of(MessageTagType.APPOINTMENT_UPDATE, "APPOINTMENT_UPDATE"),
      Arguments.of(MessageTagType.GAME_EVENT, "GAME_EVENT"),
      Arguments.of(MessageTagType.TRANSPORT_UPDATE, "TRANSPORTATION_UPDATE"),
      Arguments.of(MessageTagType.FUNC_UPDATE, "FEATURE_FUNCTIONALITY_UPDATE"),
      Arguments.of(MessageTagType.TICKET_UPDATE, "TICKET_UPDATE")
    );
  }

  @Nested
  @DisplayName("sender action types")
  class NestedSenderActionType {
    @Test
    @DisplayName("sets sender action TYPING_ON")
    void setSenderAction_TypingOn() {
      msg.setSenderAction(SenderActionType.TYPING_ON);
      assertEquals("typing_on", msg.getSenderAction());
    }

    @Test
    @DisplayName("sets sender action TYPING_OFF")
    void setSenderAction_TypingOff() {
      msg.setSenderAction(SenderActionType.TYPING_OFF);
      assertEquals("typing_off", msg.getSenderAction());
    }

    @Test
    @DisplayName("sets sender action MARK_SEEN")
    void setSenderAction_MarkSeen() {
      msg.setSenderAction(SenderActionType.MARK_SEEN);
      assertEquals("mark_seen", msg.getSenderAction());
    }
  }

  @Test
  @DisplayName("sets recipient")
  void setRecipient() {
    msg.setRecipient(new Participant("123"));
    assertNotNull(msg.getRecipient());
  }

  @Test
  @DisplayName("sets sender")
  void setSender() {
    msg.setSender(new Participant("4546"));
    assertNotNull(msg.getSender());
  }

  @Test
  @DisplayName("sets content")
  void setContent() {
    Content c = new Content();
    msg.setContent(c);
    assertNotNull(msg.getContent());
  }

}