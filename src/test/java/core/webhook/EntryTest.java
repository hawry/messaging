package core.webhook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import net.hawry.messaging.core.webhook.Entry;

@DisplayName("Entry")
class EntryTest {
  static Entry entry = null;
  static Gson gson = null;
  @BeforeAll
  static void setUp() {
    gson = new Gson();
    String json = "{'id':'123','time':8463278463287}";

    entry = gson.fromJson(json, Entry.class);
  }

  @Test
  @DisplayName("returns correct id")
  void validId() {
    assertEquals("123", entry.getId());
  }

  @Test
  @DisplayName("returns correct time")
  void validTime() {
    assertEquals(8463278463287L, entry.getTime());
  }

  @Test
  @DisplayName("returns null if no messages")
  void nullMessaging() {
    assertNull(entry.getMessaging());
  }

  @Nested
  @DisplayName("Messaging components")
  class NestedMessagingTest {
    @Test
    @DisplayName("returns null if messaging array is empty")
    void emptyMessagingArray() {
      String json = "{'id':'123','time':23123,'messaging':[]}";
      Entry en = gson.fromJson(json, Entry.class);
      assertNull(en.getMessaging());
    }

    @Test
    @DisplayName("returns the first messaging even if multiple messages are received")
    void firstMessageReturn() {
      String json = "{'id':'123','time':23123,'messaging':[{'timestamp':1234444},{'timestamp':123455555}]}";
      Entry en = gson.fromJson(json, Entry.class);
      assertEquals(1234444L, en.getMessaging().getTimestamp());
    }
  }


}