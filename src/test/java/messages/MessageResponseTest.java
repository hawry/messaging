package messages;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import net.hawry.messaging.core.response.MessageResponse;

public class MessageResponseTest {
  static Gson gson = null;

  @BeforeClass
  public static void setUpBeforeClass() {
    gson = new Gson();
  }

  @Test
  public void testDeserialization_withCorrectInfo() {
    String json = "{\"recipient_id\": \"1254477777772919\",\"message_id\": \"mid.$cAAJsujCd2ORj_1qmrFdzhVa-4cvO\"}";
    
    MessageResponse mr = gson.fromJson(json, MessageResponse.class);

    assertEquals(mr.getMessageId(),"mid.$cAAJsujCd2ORj_1qmrFdzhVa-4cvO");
    assertEquals(mr.getRecipientId(),"1254477777772919");
  }

  @Test
  @Ignore
  public void testDeserialization_withIncorrectInfo() {
    String json = "{}";
    MessageResponse mr = gson.fromJson(json, MessageResponse.class);
    
  }
}