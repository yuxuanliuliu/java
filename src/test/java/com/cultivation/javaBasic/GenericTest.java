package com.cultivation.javaBasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cultivation.javaBasic.util.Employee;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.Manager;
import com.cultivation.javaBasic.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class GenericTest {

  @SuppressWarnings("unused")
  @Test
  void should_auto_resolve_generic_method() {
    final String[] words = {"Hello", "Good", "Morning"};

    // TODO: please call getMiddle method for string
    // <--start
    final String middle = getMiddle(words);
    Object middle1 = getMiddle(1, "1", null, 0);
//    System.out.println(middle1);
    // --end-->

    assertEquals("Good", middle);
    assertEquals("Good", middle1);
    //generic invocation, instantiate
    //type erasure replace with object or bounds
    //unbounded T replaced with object
  }

  @Test
  void should_specify_a_type_restriction_on_typed_parameters() {
    int minimumInteger = min(new Integer[]{1, 2, 3});
    double minimumReal = min(new Double[]{1.2, 2.2, -1d});
    assertEquals(1, minimumInteger);
    assertEquals(-1d, minimumReal, 1.0E-05);
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  void should_not_know_generic_type_parameters_at_runtime() {
    //cannot use instance of, cannot cast to parameterized types no reason to cast to object
    // runtime does not track type parameters
    //raw type

    KeyValuePair<String, Integer> pair = new KeyValuePair<>("name", 2);
    KeyValuePair<Integer, String> pairWithDifferentTypeParameter = new KeyValuePair<>(2, "name");

    // TODO: please modify the following code to pass the test
    // <--start
    final Optional<Boolean> expected = Optional.of(true);
    // --end-->

    assertEquals(expected.get(), pair.getClass().equals(pairWithDifferentTypeParameter.getClass()));

  }

  @Test
  void array_list() {
    ArrayList<String> str = new ArrayList<>();
    ArrayList<Integer> integer = new ArrayList<>();
    assertTrue(str.getClass().equals(integer.getClass()));
  }

  @SuppressWarnings({"UnnecessaryLocalVariable", "unused", "ConstantConditions"})
  @Test
  void should_be_careful_of_raw_type_generic() {
    /*
    can assign parameterized type to raw type not in reverse.
    raw type cannot invoke method in generic type

    Pair<Object> = Pair<Manager>
    setFirst(Object data) {
    setData ((Manager) data) }

     */
    Pair<Manager> managerPair = new Pair<>();
    Pair rawPair = managerPair;
    rawPair.setFirst(new Employee()); //cast from object to employee

    boolean willThrow = false;
    try {
    Manager first = managerPair.getFirst(); //expect manager but actual is employee
    } catch (ClassCastException error) {
      willThrow = true;
  }

  // TODO: please modify the following code to pass the test
  // <--start
    final Optional<Boolean> expected = Optional.of(true);
  // --end-->

    assertEquals(expected.get(), willThrow);
  }

  @Test
  void should_swap() {
    /*
    not able to confirm type of object
     */
    Pair<String> pair = new Pair<>("Hello", "World");

    swap(pair);

    assertEquals("World", pair.getFirst());
    assertEquals("Hello", pair.getSecond());
  }

  @SuppressWarnings("unused")
  //E list elements
  private static <T> T getMiddle(T... args) {
    return args[args.length / 2];
  }

  // TODO: please implement the following code to pass the test. It should be generic after all.
  // The method should only accept `Number` and the number should implement `Comparable<T>`
  // <--start
  @SuppressWarnings("unused")
  private static <T extends Number & Comparable<T>> T min(T[] values) {
    /* 1.restrict type in parameterized type
       2.invoke method defined in the bounds
       3.compare object use type parameter bounded to comparable
    */
//    T value = values[0];
//    for (T a : values) {
//      if (a.compareTo(value) < 0) {
//        value = a;
//      }
//    }
//    return value;
    Number[] elem = new Number[10];
    return Stream.of(values).min(Comparator.naturalOrder()).orElse(convertInstanceOfObject(elem));
  }

  private static <T> T convertInstanceOfObject(Object o) {
    try {
      return (T) o;
    } catch (ClassCastException e) {
      return null;
    }
  }


  @Test
  void cannot_instantiate_arrays() {
//    Pair<String>[] pairs = new Pair<String>[10];
    Object[] objects = new Object[10];
    objects[1] = new Pair<String>();
    objects[2] = new Pair<Integer>(); //pass array store check but still result in type error
    objects[3] = "1";
    objects[4] = 1;
    Pair<String> [] pairList; //declare without initialize
    Pair<String>[] pairs = (Pair<String>[]) new Pair<?>[10];
  }


  // --end-->

  // TODO: please implement following method to pass the test. But you cannot change the signature
  // <--start
  @SuppressWarnings("unused")
  private static void swap(Pair<?> pair) {
    swapHelper(pair);
  }

  // TODO: You can add additional method within the range if you like
  // <--start
  private static <T> void swapHelper(Pair<T> pair) {
    T first = pair.getFirst();
    pair.setFirst(pair.getSecond());
    pair.setSecond(first);
  }

  // --end-->


  @Test
  void test1() {
    Pair1<Integer, String> integerStringPair1 = new Pair1<>(1, "1");
    Pair1<Integer, String> integerStringPair11 = new Pair1<>(2, "2");
    boolean compare = Util.<Integer, String>compare(integerStringPair1, integerStringPair11);
  }
}

class Util {

  public static <K, V> boolean compare(Pair1<K, V> p1, Pair1<K, V> p2) {
    return p1.getValue().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
  }

}

class Pair1<K, V> {

  private K key;

  private V value;

  public Pair1(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return key;
  }

  public void setKey(K key) {
    this.key = key;
  }

  public V getValue() {
    return value;
  }

  public void setValue(V value) {
    this.value = value;
  }
}

interface Generic {

}

class StringList {

  static <T> List<T> emptyList() {
    return new ArrayList<T>();
  }

  ;

  Integer processStringList(List<String> list) {
    return null;
  }

  @Test
  void test() {
//    List<String> objects = StringList.emptyList();
    processStringList(StringList.emptyList());
  }

  @Test
  void cannot_create_parameterized_array() {
//    List<Integer> integers = new List<Integer>[2];
  }

  @Test
  void create_generic_array() {
    KeyValuePair[] keyValuePairs = new KeyValuePair[10];
    keyValuePairs[0] = new KeyValuePair<>("name", 10);
    System.out.println(keyValuePairs);
  }

  @Test
  void should_able_to_access_method_in_integer() {

    List<? extends Integer> intList = new ArrayList<>();
    Integer integer = intList.get(1);
    List<? extends Number> numList = intList;
  }


  class varargs {

//    @SuppressWarnings("unchecked")
    public <T> void addAll(Collection<T> coll, T... s) {
      for (T t :
          s) {
        coll.add(t);
      }
      ArrayList<Pair<String>> pairs = new ArrayList<>();
      Pair<String> pair1 = new Pair<>();
      Pair<String> pair2 = new Pair<>();
      addAll(pairs, pair1, pair2);

    }
  }

  @Test
  void name() {
    KeyValuePair[] keyValuePairs = new KeyValuePair[10];
    keyValuePairs[0] = new KeyValuePair<>("name", 10);
    keyValuePairs[1] = new KeyValuePair<>(10, "name");
    System.out.println(keyValuePairs);
  }

  @Test
  void name2() {
    KeyValuePair[] keyValuePairs = new KeyValuePair[10];
    keyValuePairs[0] = new KeyValuePair<>("name", 10);
    keyValuePairs[1] = new KeyValuePair<>(10, "name");
    KeyValuePair<String, Integer> keyValuePair = keyValuePairs[1];
  }

  @Test
  void name3() {
    KeyValuePair[] keyValuePairs = new KeyValuePair[10];
    keyValuePairs[0] = new KeyValuePair<>("name", 10);
    keyValuePairs[1] = new KeyValuePair<>(10, "name");
    KeyValuePair<String, Integer> keyValuePair = keyValuePairs[1];
    String key = keyValuePair.getKey();
  }
}




/*
 * - Can you give an example when you have to specify a typed parameter in generic method call?
 * - Can type parameter have more than one bounds? Can type parameter bounds to class? Can type parameter bounds to both
 *   class and interface? yes, bound to one class and several interfaces
 * - What if you have 2 class that one is generic called `KeyValuePair<K, V>` and the second is a non-generic method
 *   called `KeyValuePair` in the same package? no, duplicate class
 * - Can you use primitive types as type parameter? no, after type erasure, only have field object, cannot use it to store primitive type
 * - What is the result of the following code pass
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   ```
 * - What is the result of the following code pass
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   KeyValuePair<String, Integer> value = keyValuePairs[1];
 *   ```
 * - What is the result of the following code class cast exception
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   KeyValuePair<String, Integer> value = keyValuePairs[1];
 *   String key = value.getKey();
 *   ```
 * - Please describe the wildcard generic type. represent unknown type, is common parent for parameterized type
 */