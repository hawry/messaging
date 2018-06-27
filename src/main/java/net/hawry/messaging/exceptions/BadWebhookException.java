package net.hawry.messaging.exceptions;

public class BadWebhookException extends Exception {
  static final long serialVersionUID = 1L;
  public BadWebhookException(String msg) {
    super(msg);
  }
}