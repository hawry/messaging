import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import net.hawry.messaging.core.Participant;

public class ParticipantTest {
  Gson g = null;

  @Before
  public void setUp() {
    g = new Gson();
  }

  @Test
  public void testSerialization() {
    Participant p = new Participant("123428");
    String json = g.toJson(p);
    assertEquals(json, "{\"id\":\"123428\"}");
  }

  @Test
  public void testDeserialization() {
    String json = "{\"id\":\"123428\"}";
    Participant p = g.fromJson(json, Participant.class);
    assertEquals(p.getId(),"123428");
  }
}