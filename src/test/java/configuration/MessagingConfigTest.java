package configuration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import net.hawry.messaging.configuration.MessagingConfig;

@DisplayName("MessagingConfig")
class MessagingConfigTest {
  static MessagingConfig config = null;
  static String token = "anExampleToken";

  @BeforeAll
  static void setUp() {
    config = MessagingConfig.getInstance();
    config.setPageAccessToken(token);
  }

  @Test
  @DisplayName("token is returned correctly")
  void test_correctToken() {
    assertEquals(token, config.getPageAccessToken());
  }

  @Test
  @DisplayName("token is part of the remote endpoint")
  void test_tokenEndpoint() {
    String endpoint = config.getRemoteEndpointURI();
    String endpointToken = (String)endpoint.subSequence(endpoint.length() - token.length(), endpoint.length());

    assertEquals(token, endpointToken);
  }

  // https://www.petrikainulainen.net/programming/testing/junit-5-tutorial-writing-nested-tests/
  // @BeforeAll
  //   static void beforeAll() {
  //       System.out.println("Before all test methods");
  //   }
 
  //   @BeforeEach
  //   void beforeEach() {
  //       System.out.println("Before each test method");
  //   }
 
  //   @AfterEach
  //   void afterEach() {
  //       System.out.println("After each test method");
  //   }
 
  //   @AfterAll
  //   static void afterAll() {
  //       System.out.println("After all test methods");
  //   }
}