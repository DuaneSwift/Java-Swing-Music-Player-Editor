package cs3500.music.model;

/**
 * Represents a Playable Object
 */
public abstract class Playable implements IPlayable {
  private final int duration;
  private final int volume;
  private final int startbeat;
  private final int instrument;

  Playable(int duration, int volume, int startbeat, int instrument) {
    if (duration >= 0) {
      this.duration = duration;
    } else {
      throw new IllegalArgumentException(String.format("Invalid Duration: %d", duration));
    }
    if (startbeat >= 0) {
      this.startbeat = startbeat;
    } else {
      throw new IllegalArgumentException(String.format("Invalid Starting Beat: %d", startbeat));
    }
    if (volume >= 0) {
      this.volume = volume;
    } else {
      throw new IllegalArgumentException(String.format("Invalid Note Volume: %d", volume));
    }
    if (instrument >= 0) {
      this.instrument = instrument;
    } else {
      throw new IllegalArgumentException(String.format("Invalid Note Instument: %d", instrument));
    }
  }

  /*******************************************************************************************/
  /**************************************OVERRIDDEN*******************************************/
  /*******************************************************************************************/

  /**
   * Overrides equals to compare instances of Playables.
   */

  public abstract int compareTo(Playable playable);

  // Gets Start Beat
  public int getStartBeat() {
    return startbeat;
  }

  // Gets Duration
  public int getDuration() {
    return duration;
  }

  // Gets Volume
  public int getVolume() {
    return volume;
  }

  //Gets instrument number
  public int getInstrument() {
    return instrument;
  }


  /**
   * Overrides equals to compare instances of Playables.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof Playable) {
      Playable n2 = (Playable) object;
      return ((this.getPitch() == n2.getPitch()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.getPitch() + this.getOctave() * 46 + this.getVolume() + this.getStartBeat() / 5;
  }
}
