package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.hawry.messaging.core.Participant;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Participant")
class ParticipantTest {
  @Test
  @DisplayName("new participant returns correct id")
  void validId() {
    Participant p = new Participant("123");
    assertEquals("123", p.getId());
  }
}