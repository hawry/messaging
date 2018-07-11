package core.webhook;

import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import net.hawry.messaging.core.webhook.Event;

@DisplayName("Event")
class EventTest {
  static Gson gson = null;
  static Event event = null;
  @BeforeAll
  static void setUp() {
    gson = new Gson();

    String json = "{'object':'page', 'entry': [{'id':'123'},{'id':'1234'}]}";
    event = gson.fromJson(json, Event.class);
  }

  @Test
  @DisplayName("returns correct object value")
  void validObjectValue() {
    assertEquals("page", event.getObject());
  }

  @Test
  @DisplayName("returns entry array")
  void validEntryArray() {
    assertEquals(2, event.getEntries().length);
  }
}