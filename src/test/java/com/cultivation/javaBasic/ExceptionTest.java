package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.StackFrameHelper;
import com.cultivation.javaBasic.util.ClosableStateReference;
import com.cultivation.javaBasic.util.ClosableWithException;
import com.cultivation.javaBasic.util.ClosableWithoutException;
import com.cultivation.javaBasic.util.MyClosableType;
import com.cultivation.javaBasic.showYourIntelligence.StringFormatException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ExceptionTest {

  @Test
  void should_customize_exception() {
    try {
      throw new StringFormatException("the message");
    } catch (StringFormatException error) {
      assertEquals("the message", error.getMessage());
    }
  }

  @Test
  void should_customize_exception_continued() {
    Exception innerError = new Exception("inner error");
    try {
      throw new StringFormatException("the message", innerError);
    } catch (StringFormatException error) {
      assertEquals("the message", error.getMessage());
      assertEquals(innerError, error.getCause());
    }
  }


  @Test
  void should_get_suppressed_exception() throws IOException, GetSuppresedException {
//    try {
      createSuppressedException("/abcd");
//    } catch (GetSuppresedException e) {
//      System.out.println(e);
//    }
  }

  void createSuppressedException(String filePath) throws IOException, GetSuppresedException {
    FileInputStream fileIn = null;
    try {
      fileIn = new FileInputStream(filePath);
    } catch (FileNotFoundException e) {
      throw new GetSuppresedException("test", e.getCause(), true, false);
    } finally {
      try {
      fileIn.close();
      } catch (NullPointerException e) {
        throw new GetSuppresedException("2=====", e.getCause(), true, false);
      }
    }
  }

  @Test
  void should_get_cause() {
    try {
      shouldGetCause();
    } catch (Exception e
    ) {
      System.out.println(e.getMessage() + e.getCause());
    }
  }

  void shouldGetCause() throws Exception {
    try {
      throw new RuntimeException();
    } catch (RuntimeException e) {
      throw new Exception("EXCEPTION", e);
    }
  }

  @Test
  void should_be_careful_of_the_order_of_finally_block() {
    int confusedResult = confuse(2);

    // TODO: please modify the following code to pass the test
    // <--start
    final int expectedResult = 0;
    // --end-->

    assertEquals(expectedResult, confusedResult);
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  void should_use_the_try_pattern() {
    ClosableStateReference closableStateReference = new ClosableStateReference();

//    try (
//        MyClosableType closable1 = new MyClosableType(closableStateReference)) {
//      MyClosableType closable = new MyClosableType(closableStateReference);
//      assertFalse(closable.isClosed());
//      assertFalse(closable1.isClosed());
//    }
    MyClosableType myClosableType = null;
    try {
      myClosableType = new MyClosableType(closableStateReference);
    } finally {
      closableStateReference.close();
      assertFalse(myClosableType.isClosed());
    }

    // TODO: please modify the following code to pass the test
    // <--start
    final Optional<Boolean> expected = Optional.of(true);
    // --end-->

    assertEquals(expected.get(), closableStateReference.isClosed());
  }

  @SuppressWarnings({"EmptyTryBlock", "unused"})
  @Test
  void should_call_closing_even_if_exception_throws() throws Exception {
    ArrayList<String> logger = new ArrayList<>();

    try {
      try (AutoCloseable withoutThrow = new ClosableWithoutException(logger);
          AutoCloseable withThrow = new ClosableWithException(logger)) {
      }
    } catch (Exception e) {
      // It is okay!
    }

    // TODO: please modify the following code to pass the test
    // <--start
    final String[] expected = {"ClosableWithException.close", "ClosableWithoutException.close"};
    // --end-->

    assertArrayEquals(
        expected,
        logger.toArray());
  }

  @Test
  void should_get_method_name_in_stack_frame() {
    String methodName = StackFrameHelper.getCurrentMethodName();

    assertEquals(
        "com.cultivation.javaBasic.ExceptionTest.should_get_method_name_in_stack_frame",
        methodName);
  }


  @SuppressWarnings({"ReturnInsideFinallyBlock", "SameParameterValue"})
  private int confuse(int value) {
    ClosableStateReference closableStateReference = new ClosableStateReference();

    try {
      MyClosableType closable1 = new MyClosableType(closableStateReference);
    } catch (Error e) {
      return 1;
    } finally {
      closableStateReference.close();
      return 2;
    }
  }

  @Test
  private int name() throws FileNotFoundException {
    throw new FileNotFoundException();
  }

  @Test
  void should_pick_appropriate_constructor() {
    try {
      demoSuppressedException("/non-existent-path/non-existent-file.txt");
    } catch (Exception e) {
      System.out.println(e.getCause());
    }
  }

  private void demoSuppressedException(String filePath) throws IOException {
    Throwable firstException = null;
    FileInputStream fileIn = null;
    try {
      fileIn = new FileInputStream(filePath);
    } catch (IOException e) {
      firstException = e;
    } finally {
      try {
        fileIn.close();
      } catch (NullPointerException error) {
        error.addSuppressed(firstException);
        throw error;
      }
    }
  }


}

/*
 * - Please draw the hibachi of `Throwable` and explain the main purpose for each type.
 * - When do you have to declare a exception in the method signature.
 *   method is invoked by other methods
 * - When you declare a class A in package PA, and A contains a method
 *   `callMeToDeath() throw FileNotFoundException`. Package PB imports PA and uses
 *   `PA.A.callMeToDeath()`. Both PA and PB are built and deployed. Now PA is updated and
 *   the method `callMeToDeath()` throws another exception. Can we just build and replace
 *   PA?
 * no you need to handle new exception in PB
 * - Can you declare a method throws unchecked exceptions?
 * yes
 * - If a super class method throws no checked exception, could a derived class override its
 *   method and throw checked exceptions?
 *
 * no, subclass can declare unchecked exception but not checked exception
 *
 * - Which constructor do you want to implement if you provide your own exception.
 *
 */
