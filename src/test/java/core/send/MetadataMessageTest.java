package core.send;

import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import net.hawry.messaging.core.send.MetadataMessage;

@DisplayName("MetadataMessage")
class MetadataMessageTest {
  static Gson gson = null;
  static String longstring = "";

  @BeforeAll
  static void setUp() {
    gson = new Gson();

    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < 1010; i++) {
      buf.append("a");
    }
    longstring = buf.toString();
  }

  @Test
  @DisplayName("sets a normal length metadata message")
  void normalMetadata() {
    MetadataMessage m = new MetadataMessage();
    m.setMetadata("metadata as string");
    assertEquals("metadata as string", m.getMetadata());
  }

  @Test
  @DisplayName("cuts of > 1000 chars at 1000")
  void exceedsMetadata() {
    MetadataMessage m = new MetadataMessage();
    m.setMetadata(longstring);
    assertEquals(1000, m.getMetadata().length());
  }
}