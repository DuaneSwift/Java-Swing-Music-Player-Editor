package cs3500.music.model;


/**
 * Represents the concepts of a repeat i.e., the idea that all repeats have a start, end and a loop
 * number.
 */
public abstract class Repeat {
  private int iterations;
  private int start;
  private int end;

  /**
   * The concept of an abstract repeat
   *
   * @param iterations loop number
   * @param start      start beat
   * @param end        end beat
   */
  Repeat(int iterations, int start, int end) {
    this.iterations = iterations;
    this.start = start;
    this.end = end;
  }

  /**
   * Iterations/Loops
   *
   * @return the number of iterations/loops of this repeat
   */
  public int getIterations() {
    return iterations;
  }

  /**
   * @return the start of this repeat
   */
  public int getStart() {
    return start;
  }

  /**
   * @return the end of this repeat
   */
  public int getEnd() {
    return end;
  }
}

