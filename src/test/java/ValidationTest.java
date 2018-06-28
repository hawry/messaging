import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.hawry.messaging.core.*;
import net.hawry.messaging.core.Message;
import net.hawry.messaging.core.Participant;
import net.hawry.messaging.exceptions.MissingRequiredFieldException;

public class ValidationTest {
  @Rule 
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testWithoutAnything() throws MissingRequiredFieldException {
      Message m = new Message();
      thrown.expect(MissingRequiredFieldException.class);
      thrown.expectMessage("Missing required field(s): [messaging_type, content, recipient]");
      m.toJson();
    }

    @Test
    public void testWithoutRecipient() throws MissingRequiredFieldException {
      Content c= new Content();
      c.setText("just something");
      
      Message m = new Message();
      m.setMessageType(MessageType.RESPONSE);
      m.setContent(c);

      thrown.expect(MissingRequiredFieldException.class);
      thrown.expectMessage("Missing required field(s): [recipient]");
      
      m.toJson();
    }

    @Test
    public void testWithEmptyRecipient() throws MissingRequiredFieldException {
      Content c= new Content();
      c.setText("just something");
      
      Message m = new Message();
      m.setMessageType(MessageType.RESPONSE);
      m.setContent(c);
      m.setRecipient(new Participant(""));

      thrown.expect(MissingRequiredFieldException.class);
      thrown.expectMessage("Missing required field(s): [recipient: Missing required field(s): id]");
      
      m.toJson();
    }

    @Test
    public void testWithoutMessageTag() throws MissingRequiredFieldException {
      Content c = new Content();
      c.setText("hello");

      Message m = new Message();
      m.setRecipient(new Participant("123"));
      m.setContent(c);
      m.setMessageType(MessageType.MESSAGE_TAG);

      thrown.expect(MissingRequiredFieldException.class);
      thrown.expectMessage("Missing required field(s): [tag]");

      m.toJson();
    }

    @Test
    public void testWithEmptyContentText() throws MissingRequiredFieldException {
      Content c = new Content();

      Message m = new Message();
      m.setRecipient(new Participant("123"));
      m.setContent(c);
      m.setMessageType(MessageType.RESPONSE);

      thrown.expect(MissingRequiredFieldException.class);
      thrown.expectMessage("Missing required field(s): [content: Missing required field(s): text]");

      m.toJson();
    }
}