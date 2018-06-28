import org.junit.Test;
import static org.junit.Assert.*;
import net.hawry.messaging.exceptions.BadWebhookException;

public class ExceptionTest {
  @Test
  public void testBadWebhookException() {
    BadWebhookException e = new BadWebhookException("this is a message");
    assertEquals(e.getMessage(),"this is a message");
  }
}