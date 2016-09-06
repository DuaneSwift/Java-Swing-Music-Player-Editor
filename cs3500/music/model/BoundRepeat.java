package cs3500.music.model;

/**
 * A repeat that occurs within the bounds of two beats
 */
public class BoundRepeat extends Repeat {

  /**
   * Creates a bound repeat object
   *
   * @param iterations number of loops
   * @param start      start beat
   * @param end        end beat
   */
  public BoundRepeat(int iterations, int start, int end) {
    super(iterations, start, end);
  }
}
