package net.hawry.messaging.core;

public enum MessageType {
  
  RESPONSE("RESPONSE"), UPDATE("UPDATE"), MESSAGE_TAG("MESSAGE_TAG");
  
  private String value;
  MessageType(String v) {
    value = v;
  }

  protected String getValue() {
    return value;
  }
}