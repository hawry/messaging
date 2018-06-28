package net.hawry.messaging.configuration;

public class MessagingConfig {
  private static MessagingConfig instance = null;
  private String PAGE_ACCESS_TOKEN = "";
  private String REMOTE_ENDPOINT = "https://graph.facebook.com/v2.6/me/messages?access_token=%s";

  private MessagingConfig() {}

  public static MessagingConfig getInstance() {
    if (instance == null) {
      instance = new MessagingConfig();
    }
    return instance;
  }

  /**
   * Sets the access token provided by the Messenger Platform which is required to interact with the Messenger API in any way.
   * 
   * @param token the PAGE ACCESS TOKEN generated for the specific Facebook app
   */
  public void setPageAccessToken(String token) {
    instance.PAGE_ACCESS_TOKEN = token;
  }

  /**
   * Provides a String representation of the PAGE ACCESS TOKEN.
   * 
   * @return the provided page access token
   */
  public String getPageAccessToken() {
    return instance.PAGE_ACCESS_TOKEN;
  }

  /**
   * Returns the formatted endpoint URI to use for interacting with the Messenger API.
   * 
   * @return the formatted endpoint URI with the page access token embedded into it
   */
  public String getRemoteEndpointURI() {
    return String.format(instance.REMOTE_ENDPOINT, instance.PAGE_ACCESS_TOKEN);
  }
}