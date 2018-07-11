package core.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


import com.google.gson.Gson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.hawry.messaging.core.response.MessageResponse;
import net.hawry.messaging.exceptions.InvalidJsonException;

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

  @Test
  @DisplayName("message response fromJson returns a valid instance")
  void fromJson_Valid() {
    String json = "{'recipient_id': '123','message_id':'$mid.id'}";
    MessageResponse rsp = null;
    
    try {
      rsp = MessageResponse.fromJson(json);
    } catch (InvalidJsonException e) {
      Assertions.fail("exception thrown: " + e.getMessage());
    }

    assertEquals("$mid.id", rsp.getMessageId());
    assertEquals("123", rsp.getRecipientId());
  }

  @Test
  @DisplayName("message response fromJson returns a valid error")
  void fromJson_ValidError() {
    String json = "{'error':{'code':100}}";

    MessageResponse rsp = null;
    try {
      rsp = MessageResponse.fromJson(json);
    } catch (InvalidJsonException e) {
      Assertions.fail("exception thrown: " + e.getMessage());
    }
    assertTrue(rsp.hasErrors());
    assertNotNull(rsp.getError());
  }

  @Test
  @DisplayName("invalid json throws exception")
  void fromJson_InvalidJson() {
    String json = "{'recipient_id':123";
    assertThrows(InvalidJsonException.class, () -> { MessageResponse.fromJson(json); });
  }
}