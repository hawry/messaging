import static org.junit.Assert.*;

import com.google.gson.Gson;

import org.junit.BeforeClass;
import org.junit.Test;

import net.hawry.messaging.core.response.MessageResponse;


public class MessageResponseTest {
  
  static Gson g = null;

  @BeforeClass
  public static void setUpBeforeClass() {
    g = new Gson();
  }

  @Test
  public void testResponseSerialization() {
    String json = "{\"recipient_id\": \"1254477777772919\",\"message_id\": \"mid.$cAAJsujCd2ORj_1qmrFdzhVa-4cvO\"}";
    MessageResponse response = g.fromJson(json, MessageResponse.class);
    assertEquals(response.getMessageId(), "mid.$cAAJsujCd2ORj_1qmrFdzhVa-4cvO");
    assertEquals(response.getRecipientId(), "1254477777772919");
  }

  public static class ErrorResponses {

    @Test
    public void testHasErrors() {
      Gson g = new Gson();

      MessageResponse response = g.fromJson("{\"error\": {\"message\": \"Invalid OAuth access token.\",\"type\": \"OAuthException\",\"code\": 190,\"error_subcode\":1234567,\"fbtrace_id\": \"BLBz/WZt8dN\"}}", MessageResponse.class);

      assertTrue(response.hasErrors());
      assertNull(response.getMessageId());
      assertNull(response.getRecipientId());

      assertEquals("Invalid OAuth access token.", response.getError().getErrorMessage());
      assertEquals(190,response.getError().getErrorCode());
      assertEquals(1234567,response.getError().getErrorSubcode());
      assertEquals("BLBz/WZt8dN", response.getError().getTraceId());
      assertEquals("Access Token Error", response.getError().getErrorCategory());
    }
  }

  public static class FromJson {
    @Test
    public void testFromJson_WithValidJsonAndNoError() {
      String payload = "{'recipient_id':'12345','message_id':'$mid.randomchars'}";
    }

    @Test
    public void testFromJson_WithInvalidJsonAndNoError() {
      String payload = "{'recipient_id':,'message_id':'$mid.randomchars'}";
    }
  }
}