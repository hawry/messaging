package core.response;

import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import net.hawry.messaging.core.response.MessageResponse;
import net.hawry.messaging.core.response.ResponseError;

@DisplayName("ResponseError")
class ResponseErrorTest {
  static Gson gson = null;
  static MessageResponse mr = null;
  static ResponseError re = null;
  
  @BeforeAll
  static void setUp() {
    gson = new Gson();
    String json = "{\"error\": {\"message\": \"Invalid OAuth access token.\",\"type\": \"OAuthException\",\"code\": 190,\"error_subcode\":1234567,\"fbtrace_id\": \"BLBz/WZt8dN\"}}";
    mr = gson.fromJson(json, MessageResponse.class);
    re = mr.getError();
  }

  @Test
  @DisplayName("message id and recipient id are null")
  void optionalFieldsAreNull() {
    assertNull(mr.getMessageId());
    assertNull(mr.getRecipientId());
  }

  @Test
  @DisplayName("has error returns TRUE")
  void hasError() {
    assertTrue(mr.hasErrors());
  }

  @Test
  @DisplayName("returns correct error code")
  void validErrorCode() {
    assertEquals(190, re.getErrorCode());
  }

  @Test
  @DisplayName("returns correct error subcode")
  void validSubcode() {
    assertEquals(1234567, re.getErrorSubcode());
  }

  @Test
  @DisplayName("returns correct error type")
  void validErrorType() {
    assertEquals("OAuthException", re.getErrorType());
  }

  @Test
  @DisplayName("returns correct trace id")
  void validTraceId() {
    assertEquals("BLBz/WZt8dN", re.getTraceId());
  }
  
  @Test
  @DisplayName("returns correct error category")
  void validErrorCategory() {
    assertEquals("Access Token Error", re.getErrorCategory());
  }

  @Test
  @DisplayName("returns correct error message")
  void validErrorMessage() {
    assertEquals("Invalid OAuth access token.", re.getErrorMessage());
  }


}