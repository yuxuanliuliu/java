package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.DistinctIterable;
import com.cultivation.javaBasic.showYourIntelligence.Sequence;
import com.cultivation.javaBasic.util.RandomCharacterIterable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.cultivation.javaBasicExtended.myUnitTestFramework.MyAssert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class CollectionsTest {

  @Test
  void should_go_through_an_iterator() {
    ArrayList<String> collection = new ArrayList<>();
    collection.add("Hello");
    collection.add("World");
    collection.add("!");

    LinkedList<String> collection2 = new LinkedList<>();
    collection2.add("Hello");
    collection2.add("World");
    collection2.add("!");

//    HashSet<String> collectionSet = new HashSet<>(collection);
//    HashSet<String> collectionSet2 = new HashSet<>(collection2);

    assertEquals(collection, collection2);
    assertIterableEquals(collection, collection2);
//    assertEquals(collectionSet, collectionSet2);
//    assertIterableEquals(collectionSet, collectionSet2);
//  assertIterableEquals(collection, collection2);

    Iterator<String> iterator = collection.iterator();

//    assertIterableEquals(collection, createList(iterator));
  }

  @SuppressWarnings({"unused", "UnnecessaryLocalVariable"})
  private static List<String> createList(Iterator<String> iterator) {
    List<String> list = new ArrayList<>();

    // TODO: you could ONLY use `Iterator.hasNext` and `Iterator.next` API to copy items to a `List`. No `for` is
    // allowed.
    // <--start
    while (iterator.hasNext()) {
      String element = iterator.next();
      list.add(element);
    }
    return list;
    // --end-->
  }

  @Test
  void should_create_a_sequence_without_putting_all_items_into_memory() {
    Sequence sequence = new Sequence(4, 10);

    ArrayList<Object> sequenceArray = new ArrayList<>();
    for (int a : sequence ) {
      sequenceArray.add(a);
    }

    assertIterableEquals(Arrays.asList(4, 5, 6, 7, 8, 9, 10), sequence);
    Assertions.assertEquals(Arrays.asList(4, 5, 6, 7, 8, 9, 10), sequenceArray);
    //
  }

  @Test
  void should_predict_linked_list_operation_result() {
    LinkedList<String> staff = new LinkedList<>();

    staff.add("Amy");
    staff.add("Bob");
    staff.add("Carl");

//    staff.add(1, "juliet");
//    staff.remove(1);

    System.out.println(staff);

    ListIterator<String> iterator = staff.listIterator();
    iterator.next();
    iterator.add("Juliet");
    staff.add("mary");
    iterator.previous();
    iterator.remove();

    // TODO: please modify the following code to pass the test
    // <--start
    final List<String> expected = Arrays.asList("Amy", "Bob", "Carl");
    // --end-->

    assertIterableEquals(expected, staff);
  }

  @Test
  void should_generate_distinct_sequence_on_the_fly() {
    // NOTE: This test may execute for a while. But it is okay if your impl is correct.
    final int oneGagaChars = 1024 * 1024 * 1024;
    //1073741824
    //2147483647
    RandomCharacterIterable characters = new RandomCharacterIterable(
        5,
        new Character[]{'a', 'b'});

//    int maxValue = Integer.MAX_VALUE;
//    System.out.println(maxValue);
//
//
//    ArrayList<Object> noArgConstructor = new ArrayList<>();
//    noArgConstructor.add(1);
//    ArrayList<Object> argConstructor = new ArrayList<>(oneGagaChars);
//    argConstructor.add("1");
//
//    assertIterableEquals(Arrays.asList(1), argConstructor);
//    //异常
//
    List<Character> distinct = new DistinctIterable<>(characters).toList();
    distinct.sort(Character::compareTo);

    assertIterableEquals(Arrays.asList('a', 'b'), distinct);
  }
  @Test
  void should_reflects_back_to_original_list_for_sub_range() {
    List<Integer> integers = new ArrayList<>();
    for (int i = 0; i < 12; ++i) {
      integers.add(i);
    }

    List<Integer> subList = integers.subList(3, 10);


    subList.clear();

    // TODO: please modify the following code to pass the test
    // <--start
    final List<Integer> expected = Arrays.asList(0, 1, 2, 10, 11);
    // --end-->
    assertIterableEquals(expected, integers);
  }


  @Test
  void should_reflects_back_to_original_list_for_sub_range_1() {
    List<Integer> integers = new ArrayList<>();
    for (int i = 0; i < 12; ++i) {
      integers.add(i);
    }

    List<Integer> subList = integers.subList(3, 10);
    subList.clear();

    // TODO: please modify the following code to pass the test
    // <--start
    Iterator<Integer> iterator = integers.iterator();

    final List<Integer> expected = Arrays.asList(0, 0, 0);
    int[] a = {1, 2, 3};
    List<int[]> ints = Arrays.asList(a);
    expected.set(0, 1);
    System.out.println(expected);
    List<String> strings = Collections.nCopies(2, "1");

    // --end-->

    assertIterableEquals(expected, integers);
  }

  @Test
  void name() {
    ArrayList<Object> objects2 = new ArrayList<>();
    List<Object> objects = Arrays.asList(null);
  }
}



/*
 * - Can you expect the order returned when iterating over a `HashSet<T>`? no
 * - What is an `ArrayList`, `LinkedList`, `ArrayDeque`, `HashSet`, `HashSet`, `TreeSet`, `EnumSet`, `LinkedHashSet`,
 *   `PriorityQueue`, `HashMap`, `TreeMap`, `EnumMap`, `LinkedHashMap`
 * - What if an collection is modified while an iterator is still iterating? conCurrentmodified exception
 * - Can you add or remove items to the list that is returned by `Array.asList` or `Collections.nCopies`?
 * nCopies is immutable
 * Arrya.asList changes the original list
 * - What are the differences between HashMap and HashSet?
 * hashset is implemented by hashmap
 * - What is size(), and what capacity?
 * size is number of element in collection, capacity can be initialized when declare first or grow as adding.
 */
