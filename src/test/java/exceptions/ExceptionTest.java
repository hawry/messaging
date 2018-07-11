package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import net.hawry.messaging.exceptions.InvalidJsonException;
import net.hawry.messaging.exceptions.MissingRequiredFieldException;

@DisplayName("string formatting of exception")
class ExceptionTest {

  @Nested
  @DisplayName("InvalidJsonException")
  class InvalidJsonExceptionTest {
    @Test
    @DisplayName("shows correct classname")
    void invalidJsonException() {
      Exception e = new InvalidJsonException("ExampleClass");
      assertEquals("JSON string is not valid for class ExampleClass", e.getMessage());
    }
  }

  @Nested
  @DisplayName("MissingRequiredFieldException")
  class MissingRequiredFieldExceptionTest {
    @Test
    @DisplayName("shows single missing field")
    void singleMissingField() {
      MissingRequiredFieldException e = new MissingRequiredFieldException("example");
      assertEquals("Missing required field(s): example", e.getMessage());
    }

    @Test
    @DisplayName("shows multiple missing fields")
    void multipleMissingFields() {
      ArrayList<String> l = new ArrayList<String>();
      l.add("first");
      l.add("second");
      l.add("third");

      MissingRequiredFieldException e = new MissingRequiredFieldException(l);

      assertEquals("Missing required field(s): [first, second, third]", e.getMessage());
    }
  }
  
}