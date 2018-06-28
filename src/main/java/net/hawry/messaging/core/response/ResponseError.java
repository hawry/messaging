package net.hawry.messaging.core.response;

import com.google.gson.annotations.SerializedName;

public class ResponseError {
  private final String INTERNAL = "Internal Error";
  private final String LIMIT = "Limit Error";
  private final String UNKNOWN = "Unknown";
  private final String BAD_PARAM = "Bad Parameter Error";
  private final String ACCESS = "Access Token Error";
  private final String PERMISSION = "Permission Error";
  private final String ACCOUNT = "Account-Linking Error";
  private final String THREAD = "Thread Owner API Error";


  @SerializedName("message") String message;
  @SerializedName("type") String type;
  @SerializedName("code") int code;
  @SerializedName("error_subcode") int subcode;
  @SerializedName("fbtrace_id") String traceId;

  /**
   * @return the error message in plain text
   * @see <a href="https://developers.facebook.com/docs/messenger-platform/reference/send-api/error-codes">https://developers.facebook.com/docs/messenger-platform/reference/send-api/error-codes</a>
   */
  public String getErrorMessage() {
    return this.message;
  }

  /**
   * @return the error type
   * @see <a href="https://developers.facebook.com/docs/messenger-platform/reference/send-api/error-codes">https://developers.facebook.com/docs/messenger-platform/reference/send-api/error-codes</a>
   */
  public String getErrorType() {
    return this.type;
  }

  /**
   * @return error code
   * @see <a href="https://developers.facebook.com/docs/messenger-platform/reference/send-api/error-codes">https://developers.facebook.com/docs/messenger-platform/reference/send-api/error-codes</a>
   */
  public int getErrorCode() {
    return this.code;
  }

  /**
   * @return error subcode
   * @see <a href="https://developers.facebook.com/docs/messenger-platform/reference/send-api/error-codes">https://developers.facebook.com/docs/messenger-platform/reference/send-api/error-codes</a>
   */
  public int getErrorSubcode() {
    return this.subcode;
  }

  /**
   * @return Messenger Platform trace id
   */
  public String getTraceId() {
    return this.traceId;
  }
  /**
   * Returns the error category that the error represents. Is one of the following: Internal Errors, Limit Errors, Bad Parameter Errors, Access Token Error, Permission Errors, Account-Linking Errors, Thread Owner API Errors
   * 
   * @return the string representation of the error category
   * @see <a href="https://developers.facebook.com/docs/messenger-platform/reference/send-api/error-codes">https://developers.facebook.com/docs/messenger-platform/reference/send-api/error-codes</a>
   */
  public String getErrorCategory() {
    switch (code) {
      case 1200:
        return INTERNAL;
      case 100:
        // a lot of different subcodes here...
        switch (subcode) {
          case 2018109: case 2018144:
            return LIMIT;
          default:
            return BAD_PARAM;
        }
      case 613:
        return LIMIT;
      case 4:
        switch (subcode) {
          default:
            return LIMIT;
        }
      case 190:
        return ACCESS;
      case 10: case 200:
        return PERMISSION;
      case 10303:
        return ACCOUNT;
      case 201817: case 2018234: case 2018237:
        return THREAD;
    }
    return UNKNOWN;
  }
}