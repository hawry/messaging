package core;

import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import net.hawry.messaging.core.*;

@DisplayName("Content")
class ContentTest {
  static Gson gson = null;
  static String longstring = "";

  @BeforeAll
  static void setUp() {
    gson = new Gson();

    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < 2010; i++) {
      buf.append("a");
    }
    longstring = buf.toString();
  }
  
  @Nested
  @DisplayName("setText")
  class ContentSerializationTest {
    @Test
    @DisplayName("sets and returns correct number of characters when under allowed limit")
    void setText_AllowedLimit() {
      Content c = new Content();
      c.setText("this is a short text");

      assertEquals("this is a short text", c.getText());
    }

    @Test
    @DisplayName("sets maximum 2000 characters")
    void setText_ExceedLimit() {
      Content c = new Content();
      c.setText(longstring);
      assertEquals(2000, c.getText().length());
    }
  }
}