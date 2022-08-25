package com.cultivation.javaBasic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static java.lang.Float.valueOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FloatingTypeTest {
    @Test
    void should_not_get_rounded_result_if_convert_floating_number_to_integer() {
        final float floatingPointNumber = 2.75f;
        double v = 2.0d;
        final int integer = (int)floatingPointNumber;
        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @Test
    void cast() {
        int a = 123456789;
        byte a1 = (byte) a;
    }

    @Test
    void positive_infinity() {
        double a = 1.9e10;
        double b = 2e10;
        double v = a * b;
        System.out.println(v);
    }

    @Test
    void arithmetic_on_floats() {
        float f1 = 1.1f;
        double d1 = 0.1;
        int i = 2;

//        double v = d1 + i;
//        double v1 = f1 + d1;

        //should use big decimal
        BigDecimal bigDecimal1 = new BigDecimal(f1); //不推荐float double构造方法，初始化对象时精度丢失
        BigDecimal bigDecimal2 = new BigDecimal(d1);
        System.out.println(bigDecimal1);
        System.out.println(bigDecimal2);

        BigDecimal bigDecimal4 = new BigDecimal(Float.toString(f1)); //精确转换使用String构造器
        BigDecimal bigDecimal3 = new BigDecimal(Double.toString(d1)); //精确转换使用String构造器
        BigDecimal sum = bigDecimal4.add(bigDecimal3);
        System.out.println(sum);

        double v1 = 1.1;
        double v2 = 1.2;
        double v3 = 1.3;
        double v4 = 1.4;
        double v5 = 1.5;
        double v6 = 1.6;
        double v7 = 0.1;
        float v8 = 0.1f;
        double sum1 = v1 + v2;

        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);
        System.out.println(v5);
        System.out.println(v6);
        System.out.println(v7);
        System.out.println(v8);
        System.out.println(sum1);
    }

    @Test
    void arithmetics() {
        //为什么float0.3一样，double的不一样
        //double 0.3 相差5.551115123125783E-17
        float a = 0.1f;
        float b = 0.2f;
        float c =  a + b;
       assertEquals(0.3f, c);
       assertEquals(0, c - 0.3f);

        double a1 = 0.1;
        double b1 = 0.2;
        double c1 = a1 + b1;
        assertEquals(0.3, c1);
        assertEquals(0, c1 - 0.3);
        assertEquals(Math.pow(2, -54), c1 - 0.3);
    }

    @Test
    void should_receive_NaN() {
        //除0不会报错 结果nan
        float x = 0.0f / 0.0f;
        float y = 0.0f / 0;
        //positive infinity
        double z = 1.0 / 0;
        double a = 1.0 / 0.0;

        double b = 0.0 / 0.0;
        double c = 0.0 / 0;
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    @Test
    void bigdecimal_rounding_mode() {
        int a = 1;
        int b = 8;

        float c = 0.01f, d = 0.03f, floatTotal = 0.0f;
        BigDecimal abigDecimal = new BigDecimal(a);
        BigDecimal bbigDecimal1 = new BigDecimal(b);

        Double aDbl = (double) a;
        Double bDbl = (double) b;

        Float aFlt = (float) a;
        Float bFlt = (float) b;


        System.out.println("a/b float" + aFlt/bFlt);
        System.out.println("a/b double" + aDbl/bDbl);
        System.out.println("a/b big decimal" + abigDecimal.divide(bbigDecimal1, 5, RoundingMode.UNNECESSARY));
        //no rounding
        System.out.println("a/b big decimal" + abigDecimal.divide(bbigDecimal1, 2, RoundingMode.HALF_UP));
    }

    @Test
    void get_float_bits() {
        int floatToIntBits = Float.floatToIntBits(0.2f); //representation of floating value according to 754
        String toBinaryString = Integer.toBinaryString(floatToIntBits); //unsigned base2
    }

    @Test
    void big_decimal_division() {
        double d1 = 4.5;
        double d2 = 1.3;

        BigDecimal bigDecimal1 = new BigDecimal(d1);
        BigDecimal bigDecimal2 = new BigDecimal(d2);
        //不能整出会报错
        System.out.println(BigDecimal.valueOf(d1).divide(BigDecimal.valueOf(d2)));
        //保留小数点后四位 向 向 正无穷 的方向舍
        System.out.println(bigDecimal1.divide(bigDecimal2, 4, RoundingMode.HALF_UP));
    }

    @Test
    void float_to_long() {
        float v = 2.75f;
        byte v1 = (byte) v;
    }

    @Test
    void auto_box() {
        Float f1= 0.2f;
        long longValue = f1.longValue();
    }

    @SuppressWarnings({"divzero", "NumericOverflow"})
    @Test
    void should_judge_special_double_cases() {
        assertTrue(isInfinity(1d / 0d));
        assertTrue(isInfinity(-1d / 0d));
        assertFalse(isInfinity(2d));
        assertFalse(isInfinity(Double.NaN)); //is

        assertTrue(isNan(0d / 0d));
        assertFalse(isNan(Double.NEGATIVE_INFINITY));
        assertFalse(isNan(Double.POSITIVE_INFINITY));
    }

    @Test
    void should_not_round_number_when_convert_to_integer() {
//        final float floatingPointNumber = 2.75f;
        final float floatingPointNumber2 = 2.45f;
        final int integer = (int)floatingPointNumber2;
//        final int integer = (long)floatingPointNumber2;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @SuppressWarnings("unused")
    @Test
    void should_round_number() {
        final double floatingPointNumber = 2.75;

        // TODO: Please call some method to round the floating point number.
        // <!--start
//        final long rounded = (long) (floatingPointNumber + 0.3);
        final long rounded = Math.round(floatingPointNumber);
        // --end-->

        assertEquals(3L, rounded);
    }

    @SuppressWarnings("unused")
    private boolean isNan(double realNumber) {
        // TODO: please implement the method to pass the test.
       return Double.isNaN(realNumber);
    }

    @SuppressWarnings("unused")
    private boolean isInfinity(double realNumber) {
        // TODO: please implement the method to pass the test.
        return Double.isInfinite(realNumber);
    }

    @Test
    void name() {
        double infinity1 = Double.POSITIVE_INFINITY;
        double infinity2 = Double.POSITIVE_INFINITY;
        double infinity3 = Double.NEGATIVE_INFINITY;

        assertEquals(Double.NaN, Double.NaN);

//        assertTrue(Double.NaN == Double.NaN);


        assertTrue(isInfinityEqual(infinity1, infinity2));
        assertFalse(isInfinityEqual(infinity1, infinity3));
    }

    @Test
    void given_same_abs_float_double_equal_or_not() {
        float f1 = 0.4f;
        double d1 = 0.4;
        assertEquals(f1, d1);
       assertTrue(f1 > d1);
    }

    private boolean isInfinityEqual(double infinity1, double infinity2) {
        return infinity1 == infinity2;
    }


    /*
     * The coach should ask the following questions for the correspond test method:
     *
     * - Can we compare NaN using == directly?
     * no, never be true, all not a number are distinct
     * - Can we compare XXX_INFINITY using == directly?
     * yes, infinity represent a specific number comparing to itself
     */
}
