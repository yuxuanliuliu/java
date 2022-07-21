package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BooleanOperatorsTest {

    @SuppressWarnings({"PointlessBooleanExpression", "ConstantConditions"})
    @Test
    void should_perform_logical_boolean_operations() {
        boolean[] actualResults = {
            true && true, //true
            true && false,//false
            false && false,//false
            true || true,
            true || false,
            false || false,
            true & true, //1 1 1   //0 0 0
            true & false, //1 0 1 //0 1 0
            false & false,//0 0 0 //1 1 1
            true | true, //1 1 1 //0 0 0
            true | false, //1 0 1 true//0 1 1 true
            false | false, //0 0 0 //1 1 1
            3 == 7,
            4 != 5
        };

        // TODO: please modify the following code to pass the test
        // <--start
        boolean[] expectedResult = {true, false, false, true, true, false, true, false, false, true, true, false, false, true, };
        // --end-->

        assertArrayEquals(expectedResult, actualResults);
    }


    @Test
    void should_do_bitwise_and_boolean_operation() {
        final int value = 0x1234_abcd; //0001 0010 0011 0100 1010 1011 1100 1101
        final int mask = 0x000f_ff00; // 0000 0000 0000 1111 1111 1111 0000 0000
                                     //   0000 0000 0000 0100 1010 1011 0000 0000


        // TODO: please write down the result directly to pass the test.
        // <--start
        final int expected = 0x0004_ab00;
        // --end-->

        assertEquals(expected, value & mask);
    }

    @Test
    void should_do_bitwise_or_boolean_operation() {
        final int value = 0x1234_0000; // 0001 0010 0011 0100 0000 0000 0000 0000
        final int mask = 0x0000_abcd; //  0000 0000 0000 0000 1010 1011 1100 1101
                                     //   0001 0010 0011 0100 1010 1011 1100 1101

        // TODO: please write down the result directly to pass the test.
        // <--start
        final int expected = 0x1234_abcd;
        // --end-->

        assertEquals(expected, value | mask);
    }

    @Test
    void should_do_bitwise_not_operation() {
        final int value = 0x0000_ffff; // 0000 0000 0000 0000 1111 1111 1111 1111
                                       // 1111 1111 1111 1111 0000 0000 0000 0000

//        15*16^5 + 15*16^6 + 15*16^7 + 15*16^8
//        1*2^17 + 1*2^18+1*2^19+


        // TODO: please write down the result directly to pass the test.
        // <--start
        final int expected = -65536;
        // --end-->


        assertEquals(expected, ~value);
    }

    @Test
    void test() {
        final String value = "0xffff_0000";
        int expected = Integer.parseInt(value, 16);
        assertEquals(expected, 4294901760L);
    }
}

//eight digit hexdcimal?

