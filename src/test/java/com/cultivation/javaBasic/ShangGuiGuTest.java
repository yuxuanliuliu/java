package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

public class ShangGuiGuTest {

  @Test
  void name() {
    final byte b1 = 1;
    final byte b3 = 3;
    byte z = b3 + b1;
    /* constant variable: the variable is declared as final so the value of rhs is know at compile time
    without final the value of expression is not known at compile time, rather is evaluated at runtime;
     */
    int a = b3;
    byte c = (byte)a;
  }

  @Test
  void name1() {
    String a = 1.5f + "string";
    char b = 'b';
    System.out.println(b+ (1+ a) +1);
  }

  @Test
  void name3() {
    int n1 = 0b110;
    int n2 = 0127;
    int n3 = 0x123A;
    System.out.println(n1);
    System.out.println(n2);
    System.out.println(n3);
  }

  @Test
  void name4() {
    Integer[] i = new Integer[5];
    System.out.println(i[4]);
  }
}
