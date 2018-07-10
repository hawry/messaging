package content;

import com.google.gson.Gson;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import net.hawry.messaging.core.Content;

public class ContentTest {
  static Gson gson = null;
  static String longString = "";

  @BeforeClass
  public static void setUpBeforeClass() {
    gson = new Gson();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 2002; i++) {
      sb.append("a");
    }
    longString = sb.toString();
  }

  @Test
  public void testContent_TextMaxLimit() {
    Content c = new Content();
    c.setText(longString);
    assertEquals(2000, c.getText().length());
  }
}