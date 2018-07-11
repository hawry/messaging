package core.webhook;

import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import net.hawry.messaging.core.webhook.WebhookMessage;

@DisplayName("WebhookMessage")
class WebhookMessageTest {
  static Gson gson = null;

  @BeforeAll
  static void setUp() {
    gson = new Gson();
  }

  @Test
  @DisplayName("returns correct message id")
  void validMessageId() {
    String json = "{'mid':'$mid.messageid'}";
    WebhookMessage w = gson.fromJson(json, WebhookMessage.class);
    assertEquals("$mid.messageid", w.getMessageId());
  }
}