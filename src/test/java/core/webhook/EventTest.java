package core.webhook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.gson.Gson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.hawry.messaging.core.webhook.Event;
import net.hawry.messaging.exceptions.InvalidJsonException;

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

  @Test
  @DisplayName("returns valid Event object from valid json")
  void fromJson_ValidData() {
    String json = "{'object':'page', 'entry': [{'id':'123','time':12344444,'messaging': []}]}";
    Event event = null;

    try {
      event = Event.fromJson(json);
    } catch (InvalidJsonException e) {
      Assertions.fail("exception thrown: " + e.getMessage());
    }

    assertEquals("page", event.getObject());
    assertEquals(1, event.getEntries().length);
  }

  @Test
  @DisplayName("throws exception if invalid JSON")
  void fromJson_InvalidData() {
    String json = "{'object':'page' 'entry': [{'id':'123','time':12344444,'messaging': []}]}";
    assertThrows(InvalidJsonException.class, () -> { Event.fromJson(json); });
  }
}