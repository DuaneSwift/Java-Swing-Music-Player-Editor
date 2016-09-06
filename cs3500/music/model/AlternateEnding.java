package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The concept an alternate ending, i.e., a list of bound repeats.
 */
public class AlternateEnding {

  private final List<BoundRepeat> listofendings;

  public AlternateEnding(List<BoundRepeat> repeats) {
    listofendings = new ArrayList<>();
    listofendings.addAll(repeats);
  }

  /**
   * Returns the number of alternate endings essentially
   *
   * @return number of repeats/alternate endings
   */
  public int numberOfRepeats() {
    return listofendings.size();
  }

  /**
   * Gets the alternate ending requested
   *
   * @param i which alternate ending?
   * @return the alternate ending/bound repeat associated with i
   */
  public BoundRepeat getAlternateEnding(int i) {
    if (i > numberOfRepeats()) {
      throw new IllegalArgumentException("That ending doesn't exist");
    } else {
      return listofendings.get(i);
    }
  }


}
