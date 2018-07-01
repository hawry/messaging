import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import com.google.gson.Gson;

import org.junit.BeforeClass;
import org.junit.Test;

import net.hawry.messaging.core.webhook.Entry;

public class EntryTest {
  static Gson g = null;
  @BeforeClass
  public static void setUpBeforeClass() {
    g = new Gson();
  }

  @Test
  public void testDeSerialize() {
    String json = "{\"id\":\"123456\",\"time\":1530472304539,\"messaging\":[{\"sender\":{\"id\":\"123\"},\"recipient\":{\"id\":\"456\"},\"timestamp\":1530472304539,\"message\":{\"mid\":\"mid.1457764197618:41d102a3e1ae206a38\",\"text\":\"hello, world\"}}]}";
    Entry e = g.fromJson(json,Entry.class);

    assertEquals("123456", e.getId());
    assertEquals(1530472304539L, e.getTime());
    assertNotNull(e.getMessaging());
  }

  @Test
  public void testDeSerialize_MessagingNull() {
    String json = "{\"id\":\"123456\",\"time\":1530472304539}";
    Entry e = g.fromJson(json,Entry.class);

    assertEquals("123456", e.getId());
    assertEquals(1530472304539L, e.getTime());
    assertNull(e.getMessaging());
  }

  @Test
  public void testDeSerialize_MessagingEmpty() {
    String json = "{\"id\":\"123456\",\"time\":1530472304539,\"messaging\":[]}";
    Entry e = g.fromJson(json,Entry.class);

    assertEquals("123456", e.getId());
    assertEquals(1530472304539L, e.getTime());
    assertNull(e.getMessaging());
  }
}