package net.hawry.messaging.core;

public enum NotificationType {
  REGULAR("REGULAR"), SILENT_PUSH("SILENT_PUSH"), NO_PUSH("NO_PUSH");
  private String value;
  NotificationType(String v) {
    value = v;
  } 
  protected String getValue() {
    return value;
  }
}