package net.hawry.messaging.exceptions;

import java.util.ArrayList;

public class MissingRequiredFieldException extends Exception {
  static final long serialVersionUID = 1L;

  public MissingRequiredFieldException(String singleField) {
    super(String.format("Missing required field(s): %s", singleField));
  }

  public MissingRequiredFieldException(ArrayList<String> fields) {
    super(String.format("Missing required field(s): %s", fields.toString()));
  }
}