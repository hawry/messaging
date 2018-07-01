import com.google.gson.Gson;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import net.hawry.messaging.core.webhook.Messaging;

public class MessagingTest {
  static Gson g = null;
  @BeforeClass
  public static void setUpBeforeClass() {
    g = new Gson();
  }

  @Test
  public void testDeSerialization() {
    String json = "{'sender':{'id':'123'},'recipient':{'id':'456'},'timestamp':1530472304539,'seq':12345, 'message':{}}";
    Messaging m = g.fromJson(json, Messaging.class);
    assertEquals("123", m.getSender().getId());
    assertEquals("456", m.getRecipient().getId());
    assertEquals(1530472304539L, m.getTimestamp());
    assertNotNull(m.getMessage());
    assertEquals(12345, m.getSeq());
  }
}