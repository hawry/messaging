package net.hawry.messaging.exceptions;

public class InvalidJsonException extends Exception {
  static final long serialVersionUID = 1L;
  public InvalidJsonException(String className) {
    super(String.format("JSON string is not valid for class %s", className));
  }
}