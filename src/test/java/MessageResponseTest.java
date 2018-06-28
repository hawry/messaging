import static org.junit.Assert.*;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import net.hawry.messaging.core.MessageResponse;


public class MessageResponseTest {
  Gson g = null;
  @Before
  public void setUp() {
    g = new Gson();
  }

  @Test
  public void testResponseSerialization() {
    String json = "{\"recipient_id\": \"1254477777772919\",\"message_id\": \"mid.$cAAJsujCd2ORj_1qmrFdzhVa-4cvO\"}";
    MessageResponse response = g.fromJson(json, MessageResponse.class);
    assertEquals(response.getMessageId(), "mid.$cAAJsujCd2ORj_1qmrFdzhVa-4cvO");
    assertEquals(response.getRecipientId(), "1254477777772919");
  }
}