package main.java.ca.jrvs.apps.practice;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public interface LambdaStreamExc {
  /** Create a String Stream from array
   * note: arbitrary number of value will be stored in an array
   * @param strings
   * @return
   */
  Stream<String> createStrStream(String ... strings);

  /** Convert all strings to uppercase
   * please use createStream
   * @param strings
   * @return
   */
  Stream<String> toUpperCase(String ... strings);

  /** filter strings that contain the pattern
   * e.g
   * filter(stringStream, "a") will return another stream which no element contains a
   * @param stringStream
   * @oaram pattern
   * @return
   */
  Stream<String> filter(Stream<String> stringStream, String pattern);

  /** Create an int stream from arr[]
   *
   * @param arr
   * @return
   */
  IntStream createIntStream(int[] arr);

}
