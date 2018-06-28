package net.hawry.messaging.core;

public enum SenderActionType {
  TYPING_ON("typing_on"), TYPING_OFF("typing_off"), MARK_SEEN("mark_seen");
  private String value;
  SenderActionType(String v) {
    value = v;
  }
  protected String getValue() {
    return value;
  }
}