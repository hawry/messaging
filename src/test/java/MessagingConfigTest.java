import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import net.hawry.messaging.configuration.MessagingConfig;

public class MessagingConfigTest {
  MessagingConfig config = null;
  String testToken = "anExamplePageAccessToken";
  
  @Before
  public void setUp() {
    config = MessagingConfig.getInstance();
    config.setPageAccessToken(testToken);
  }

  @Test
  public void testGetPageAccessToken() {
    assertEquals(config.getPageAccessToken(), testToken);
  }

  @Test
  public void testGetRemoteEndpoint() {
    String endpoint = config.getRemoteEndpointURI();
    assertEquals(endpoint.subSequence(endpoint.length()-testToken.length(), endpoint.length()), testToken);
  }
}