package core.response;

import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import net.hawry.messaging.core.response.MessageResponse;

@DisplayName("MessageResponse tests")
class MessageResponseTest {
  static Gson gson = null;
  static MessageResponse mr = null;
  @BeforeAll
  static void setUp() {
    gson = new Gson();
    String json = "{\"recipient_id\": \"1254477777772919\",\"message_id\": \"mid.$cAAJsujCd2ORj_1qmrFdzhVa-4cvO\"}";

    mr = gson.fromJson(json, MessageResponse.class);
  }

  @Test
  @DisplayName("returns correct recipient id")
  void validRecipient() {
    assertEquals("1254477777772919", mr.getRecipientId());
  }

  @Test
  @DisplayName("returns correct message id")
  void validMessageId() {
    assertEquals("mid.$cAAJsujCd2ORj_1qmrFdzhVa-4cvO", mr.getMessageId());
  }

  @Test
  @DisplayName("returns no errors")
  void noErrors() {
    assertFalse(mr.hasErrors());
  }
}