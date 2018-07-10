import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.hawry.messaging.core.Content;
import net.hawry.messaging.core.Message;
import net.hawry.messaging.core.MessageType;
import net.hawry.messaging.core.Participant;
import net.hawry.messaging.exceptions.MissingRequiredFieldException;

public class MessageTest {
  Gson g = null;

  @Before
  public void setUp() {
    g = new Gson();
  }

  @Test
  public void testSerialization() {
    Message m = new Message();
    Participant sender = new Participant("123");
    Participant recipient = new Participant("456");
    m.setSender(sender);
    m.setRecipient(recipient);
    m.setMessageType(MessageType.RESPONSE);
    m.setContent(new Content());

    String json = g.toJson(m);

    assertTrue(json.contains("\"messaging_type\":\"RESPONSE\""));
    assertTrue(json.contains("\"recipient\":{\"id\":\"456\"}"));
    assertTrue(json.contains("\"sender\":{\"id\":\"123\"}"));
    // just test that the message contains a Content object, the content serialization/de-serialization should be tested in a separate file
    assertTrue(json.contains("\"message\":{}"));
  }

  @Test
  public void testDeserialization() {
    String json = "{\"messaging_type\":\"RESPONSE\",\"recipient\":{\"id\":\"456\"},\"sender\":{\"id\":\"123\"},\"message\": {\"text\":\"hello\"}}";
    Message m = g.fromJson(json, Message.class);
    assertEquals("RESPONSE", m.getMessageType());
    assertEquals("456", m.getRecipient().getId());
    assertEquals("123", m.getSender().getId());
    assertNotNull(m.getContent()); // content is tested in separate file
  }

  @Test
  public void testToJson() {
    Content c = new Content();
    c.setText("hello content");
    
    Message m = new Message();
    m.setRecipient(new Participant("123"));
    m.setMessageType(MessageType.RESPONSE);
    

    m.setContent(c);

    String json;
    try {
      json = m.toJson();
    } catch (MissingRequiredFieldException ex) {
      json = "";
      ex.printStackTrace();
    }
    assertTrue(json.contains("\"recipient\":{\"id\":\"123\"}"));
    assertTrue(json.contains("\"messaging_type\":\"RESPONSE\""));
    assertTrue(json.contains("\"message\":{\"text\":\"hello content\"}"));
  }
}