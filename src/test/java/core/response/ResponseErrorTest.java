package core.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

  @Nested
  @DisplayName("Error category")
  class ErrorCategoryTest {
    @Test
    @DisplayName("returns INTERNAL")
    void internal() {
      // code 1200
      String p = "{\"code\": 1200}";
      ResponseError r = gson.fromJson(p, ResponseError.class);
      assertEquals("Internal Error", r.getErrorCategory());
    }
    
    @DisplayName("returns LIMIT")
    @ParameterizedTest
    @CsvSource({"100,2018109", "100,2018144", "613, 0","4,0"})
    void limit(int code, int subcode) {
      String j = String.format("{'code':%d, 'error_subcode':%d}", code, subcode);
      ResponseError r = gson.fromJson(j, ResponseError.class);

      assertEquals("Limit Error", r.getErrorCategory());
      // code 100, subcode 2018109,2018144
      // code 613
    }
    void unknown() {
      String j = String.format("{'code':%d}", 32432341);
      ResponseError r = gson.fromJson(j, ResponseError.class);
      assertEquals("Unknown", r.getErrorCategory());
    }

    @Test
    @DisplayName("returns BAD_PARAM")
    void badparam() {
      String j = "{'code':100}";
      ResponseError r = gson.fromJson(j, ResponseError.class);
      assertEquals("Bad Parameter Error", r.getErrorCategory());
      // code 100, no subcode
    }

    @Test
    @DisplayName("returns ACCESS")
    void access() {
      ResponseError r = gson.fromJson("{'code':190}", ResponseError.class);
      assertEquals("Access Token Error", r.getErrorCategory());
      // code 190
    }

    @ParameterizedTest
    @DisplayName("returns PERMISSION")
    @ValueSource(ints = {10, 200})
    void permission(int code) {
      String j = String.format("{'code':%d}", code);
      ResponseError r = gson.fromJson(j, ResponseError.class);
      assertEquals("Permission Error", r.getErrorCategory());
      // code 10, 200
    }
    @Test
    @DisplayName("returns ACCOUNT")
    void account() {
      String j = String.format("{'code':%d}", 10303);
      ResponseError r = gson.fromJson(j, ResponseError.class);
      assertEquals("Account-Linking Error", r.getErrorCategory());
      // code 10303
    }

    @ParameterizedTest
    @DisplayName("returns THREAD")
    @ValueSource(ints = {201817, 2018234, 2018237})
    void thread(int code) {
      String j = String.format("{'code':%d}", code);
      ResponseError r = gson.fromJson(j, ResponseError.class);
      assertEquals("Thread Owner API Error", r.getErrorCategory());
      // code 201817, 2018234, 2018237
    }
  }


}