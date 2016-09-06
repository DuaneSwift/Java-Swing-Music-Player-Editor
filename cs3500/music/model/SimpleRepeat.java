package cs3500.music.model;

/**
 * A simple repeat, i.e., repeat the entire song n times
 */
public class SimpleRepeat extends Repeat {

  /**
   * Creates a simple repeat
   *
   * @param number number of loops
   */
  public SimpleRepeat(int number) {
    super(number, 0, 0);
  }
}
