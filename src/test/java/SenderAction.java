import com.google.gson.Gson;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import net.hawry.messaging.core.Content;
import net.hawry.messaging.core.Message;
import net.hawry.messaging.core.MessageTagType;
import net.hawry.messaging.core.MessageType;
import net.hawry.messaging.core.NotificationType;
import net.hawry.messaging.core.Participant;
import net.hawry.messaging.core.SenderActionType;
import net.hawry.messaging.exceptions.MissingRequiredFieldException;

public class SenderAction {
  static Gson g = null;
  @BeforeClass
  public static void setUpBeforeClass() {
    g = new Gson();
  }

  @Test
  public void testSenderAction_StripAll() {
    Message m = new Message();
    m.setSender(new Participant("123"));
    m.setRecipient(new Participant("456"));
    m.setMessageType(MessageType.MESSAGE_TAG);
    m.setMessageTag(MessageTagType.ALERT);
    m.setNotificationType(NotificationType.NO_PUSH);    
    m.setContent(new Content());

    m.setSenderAction(SenderActionType.MARK_SEEN);
    try {
      m.toJson();
    } catch (MissingRequiredFieldException e) {
      e.printStackTrace();
    }
    assertNull(m.getSender());
    assertNull(m.getMessageType());
    assertNull(m.getMessageTag());
    assertNull(m.getNotificationType());
    assertNull(m.getContent());

  }
}