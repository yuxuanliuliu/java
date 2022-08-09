package com.cultivation.javaBasicExtended.wordProcessor;

import java.util.List;

class TextProcessor {
    private final TextProcessorSettings settings;

    TextProcessor(int width) {
        this(width, null);
    }

    TextProcessor(int width, char[] whitespaces) {
        if (width < 10 || width > 80) {
            throw new IllegalArgumentException("Width out of range.");
        }

        settings = new TextProcessorSettings(width, getWhitespaces(whitespaces));
    }

    private char[] getWhitespaces(char[] whitespaces) {
        return whitespaces == null ?
            new char[] {' '} :
            whitespaces;
    }

    String process(String text) {
        // TODO: Please implement the method to pass all the test
        // <--start
        return "";
        // --end-->
    }
}

