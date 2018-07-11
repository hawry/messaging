package core.webhook;

import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import net.hawry.messaging.core.webhook.Messaging;

@DisplayName("Messaging")
class MessagingTest {
  static Gson gson = null;
  static Messaging msg = null;
  @BeforeAll
  static void setUp() {
    gson = new Gson();

    String json = "{'sender':{'id':'123'},'recipient':{'id':'456'},'timestamp':1345555,'seq':888,'message':{'mid':'$mid.a'}}";
    msg = gson.fromJson(json, Messaging.class);
  }

  @Test
  @DisplayName("sender is not null")
  void senderNotNull() {
    assertNotNull(msg.getSender());
  }

  @Test
  @DisplayName("recipient is not null")
  void recipientNotNull() {
    assertNotNull(msg.getRecipient());
  }

  @Test
  @DisplayName("returns correct timestamp")
  void validTimestamp() {
    assertEquals(1345555L, msg.getTimestamp());
  }

  @Test
  @DisplayName("returns correct sequence")
  void validSeq() {
    assertEquals(888, msg.getSeq());
  }

  @Test
  @DisplayName("message is not null")
  void messageNotNull() {
    assertNotNull(msg.getMessage());
  }
}