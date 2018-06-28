import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.hawry.messaging.core.Content;

public class ContentTest {
  static Gson g = null;

  @BeforeClass
  public static void setUpBeforeClass() {
    g = new Gson();
  }

  public static class MaxLimitTests {
    String longString = "";
    @Before
    public void setUp() {
      StringBuffer buf = new StringBuffer();
      for (int i = 0; i < 2001; i++) {
        buf.append("a");
      }
      longString = buf.toString();
    }

    @Test
    public void textMaxLimit_IsEnforced() {
      Content c = new Content();
      c.setText(longString);

      assertTrue(c.getText().length() == 2000);
      assertEquals(longString.subSequence(0, 2000), c.getText());
    }

    @Test
    public void metadataMaxLimit_IsEnforced() {
      Content c = new Content();
      c.setMetadata(longString);
      assertTrue(c.getMetadata().length() == 1000);
      assertEquals(longString.subSequence(0, 1000), c.getMetadata());
    }
  }

  @Test
  public void testSetData() {
    Content c = new Content();
    c.setText("hello world");
    c.setMetadata("hello metadata");

    assertEquals("hello world", c.getText());
    assertEquals("hello metadata", c.getMetadata());
  }

  @Test
  public void testSerialization() {
    Content c = new Content();
    c.setText("hello text");
    c.setMetadata("hello metadata");

    String json = g.toJson(c);

    assertTrue(json.contains("\"text\":\"hello text\""));
    assertTrue(json.contains("\"metadata\":\"hello metadata\""));
  }
}