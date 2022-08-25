package com.cultivation.javaBasic.showYourIntelligence;

import com.sun.jdi.StackFrame;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class StackFrameHelper {
    public static String getCurrentMethodName() {
        // TODO: please modify the following code to pass the test
        // <--start

        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        return stackTraceElement.getClassName().concat(".").concat(stackTraceElement.getMethodName());

//        Throwable throwable = new Throwable();
//        StackTraceElement[] stackTrace = throwable.getStackTrace();
//        return stackTrace[1].getClassName() + "." + stackTrace[1].getMethodName();

        // --end--
    }
}
