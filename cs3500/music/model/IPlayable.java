package cs3500.music.model;

/**
 * Some Generalized interface for Playables
 */
public interface IPlayable {

  /**
   * Returns the relative pitch number of a Playable
   *
   * @return int [0, undef)
   * @throws IllegalStateException if called on a pitch-less playable
   */
  int getPitch();

  /**
   * Returns the duration of a playable
   *
   * @return int [0, undef)
   */
  int getDuration();

  /**
   * Returns the volume of a playable
   *
   * @return int, [0, undef)
   */
  int getVolume();

  /**
   * Returns the octave of a Playable
   *
   * @return int, [0, undef)
   * @throws IllegalStateException if called on an octave-less playable
   */
  int getOctave();

  /**
   * Returns the starting beat of a Playable
   *
   * @return int, [0, undef)
   */
  int getStartBeat();

  /**
   * Returns the instrument number
   *
   * @return int, [0, 127]
   */
  int getInstrument();


}
