package com.cultivation.javaBasic.showYourIntelligence;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

public class Sequence implements Iterable<Integer> {

  private final Integer start;
  private final Integer end;

  public Sequence(Integer start, Integer end) {
    if (start >= end) {
      throw new IllegalArgumentException("Start must be smaller than End.");
    }
    this.start = start;
    this.end = end;
  }

  @Override
  public Iterator<Integer> iterator() {
    return new SequenceIterator(start, end);
  }
}

class SequenceIterator implements Iterator<Integer> {

  // TODO: You can add additional fields or methods if you want.
  // <--start
  private int cursor;
  private int end;
  // --end-->

  SequenceIterator(Integer start, Integer end) {
    // TODO: please implements the following code to pass the test
    // <--start
    this.cursor = start;
    this.end = end + 1;
    // --end-->
  }

  @Override
  public boolean hasNext() {
    // TODO: please implements the following code to pass the test
    // <--start
    return cursor < end;
    // --end-->
  }

  @Override
  public Integer next() {
    // TODO: please implements the following code to pass the test
    // <--start
    return cursor++;
    // --end-->
  }
}
