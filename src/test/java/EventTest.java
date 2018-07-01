import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.gson.Gson;

import org.junit.BeforeClass;
import org.junit.Test;

import net.hawry.messaging.core.webhook.Event;

public class EventTest {
  static Gson g = null;
  @BeforeClass
  public static void setUpBeforeClass() {
    g = new Gson();
  } 

  @Test
  public void testSimpleSerialization() {
    String entry = "{\"id\":\"123456\",\"time\":1530472304539,\"messaging\":[{\"sender\":{\"id\":\"123\"},\"recipient\":{\"id\":\"456\"},\"timestamp\":1530472304539,\"message\":{\"mid\":\"mid.1457764197618:41d102a3e1ae206a38\",\"text\":\"hello, world\"}}]}";
    String raw = "{\"object\":\"page\",\"entry\": [%s]}";

    String json = String.format(raw, entry);

    Event e = g.fromJson(json, Event.class);
    assertEquals("page", e.getObject());
    assertNotNull(e.getEntries());
  }
}