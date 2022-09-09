package com.cultivation.javaBasic;

import java.io.FileNotFoundException;

public class GetSuppresedException extends Throwable {

  public GetSuppresedException(String message, Throwable cause, boolean suppressed, boolean fillInStack) {
    super(message, cause, suppressed, fillInStack);
  }
}
