import com.google.gson.Gson;

import org.junit.Test;

import net.hawry.messaging.core.Message;
import net.hawry.messaging.core.MessageBuilder;
import net.hawry.messaging.core.MessageType;
import net.hawry.messaging.core.NotificationType;
import net.hawry.messaging.core.Participant;
import net.hawry.messaging.core.SenderActionType;

public class MessageBuilderTest {

  @Test
  public void doTest_RegularContent() {
    MessageBuilder mb = new MessageBuilder();
    Message m = mb
                  .setRecipient(new Participant("123"))
                  .setMessageType(MessageType.RESPONSE)
                  .setContentText("this should be a regular content")
                  .setContentText("and this should replace the previous")
                  .create();

    Gson g = new Gson();
  }

  @Test
  public void doTest_MetadataContent() {
    MessageBuilder mb = new MessageBuilder();
    Message m = mb
                  .setRecipient(new Participant("123"))
                  .setMessageType(MessageType.RESPONSE)
                  .setContentText("this should be a regular content")
                  .setContentMetadata("and this should be some metadata")
                  .setNotificationType(NotificationType.REGULAR)
                  .setSenderAction(SenderActionType.TYPING_OFF)
                  .create();

    Gson g = new Gson();
    // System.out.println(g.toJson(m));

    try {
      // System.out.println(m.toJson());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}