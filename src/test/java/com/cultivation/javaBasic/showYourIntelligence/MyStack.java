package com.cultivation.javaBasic.showYourIntelligence;

import java.util.Arrays;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class MyStack {

  private int[] storage;
  private int capacity;
  private int count;
  private static final int GROW_FACTOR = 2;

  public MyStack(int initialCapacity) {
    if (initialCapacity < 1) {
      throw new IllegalArgumentException("Capacity too small.");
    }

    capacity = initialCapacity;
    storage = new int[initialCapacity];
    count = 0;
  }

  public void push(int value) {
    if (count == capacity) {
      ensureCapacity();
    }

    // TODO: Please push the value into the storage here.
    // <--start
    storage[count++] = value;
    // --end-->
  }

  private void ensureCapacity() {
    int newCapacity = capacity * GROW_FACTOR;

    // TODO: Please create a new array of size newCapacity. And update related fields
    // TODO: You SHOULD NOT USE COLLECTIONS OTHER THAN ARRAY.
    // <--start
//  storage = Arrays.copyOf(storage, newCapacity);

    int[] newTempArray = new int[newCapacity];
    int length = storage.length;
    for (int i = 0; i < length; i++) {
      newTempArray[i] = storage[i];
    }
    capacity = newCapacity;
    storage = newTempArray;

    // --end-->
  }

  public int[] popToArray() {
    final int totalItemsCount = count;
    int[] array = new int[totalItemsCount];

    while (count > 0) {
      array[totalItemsCount - count] = pop();
    }

    return array;
  }

  private int pop() {
    // TODO: Please pop one element from the array.
    // <--start
    int number = storage[--count];
    // --end-->
    if (storage.length == 0) {

      throw new UnsupportedOperationException("Stack is empty.");
    }
    return number;
  }
}
