package cs3500.music.model;

import java.util.Collection;
import java.util.List;

/**
 * Represents the interface for the MusicEditor
 */
public interface MusicEditorModel {

  /**
   * Adds some playable to the MusicEditor
   *
   * @return {@code this}
   */
  MusicEditorModel addPlayable(IPlayable playable);

  /**
   * Removes some playable to the MusicEditor
   *
   * @return {@code this}
   */
  MusicEditorModel removePlayable(IPlayable playable);

  /**
   * Returns the playable at a particular beat in the composition
   *
   * @return Playable
   */
  Collection<IPlayable> getPlayablesAtBeat(int beat);

  /**
   * Returns the highest Playable in the composition
   *
   * @return Highest Playable
   */
  IPlayable getHighestPlayable();

  /**
   * Returns the lowest Playable in the composition
   *
   * @return Lowest Playable
   */
  IPlayable getLowestPlayable();

  /**
   * Returns the final Playable in the composition
   *
   * @return Last Playable
   */
  Collection<IPlayable> getFinalPlayables();

  /**
   * Adds simultaneous implementations of the model
   *
   * @param composition some implementation of {@code this}
   * @return {@code this}
   */
  MusicEditorModel addSimultaneous(MusicEditorModel composition);

  /**
   * Adds consecutive implementations of {@code this}
   *
   * @param composition some implementations {@code this}
   * @return {@code this}
   */
  MusicEditorModel addConsecutive(MusicEditorModel composition);

  /**
   * Returns the implementation of {@code this}
   *
   * @return {@code this}
   */
  MusicEditorModel getComposition();

  /**
   * Gets the last beat of the composition
   *
   * @return the last beat of the composition
   */
  int getLastBeat();

  /**
   * Determines whether or not a composition contains a note at a given beat number
   *
   * @param beatnumber some given beat number
   * @return whether or not it contains the beat number.
   */
  boolean containsBeat(int beatnumber);

  /**
   * Sets the tempo of the implementation
   *
   * @param tempo some tempo in milliseconds
   * @return {@code this}
   */
  MusicEditorModel setTempo(int tempo);

  /**
   * Gets the tempo of the implementation
   *
   * @return the tempo in milliseconds
   */
  int getTempo();

  /**
   * Is this implementation empty?
   *
   * @return whether or not it is empty
   */
  boolean isEmpty();

  /**
   * Returns the exact playable at a given beat
   *
   * @param pitch the pitch requested
   * @param beat  the beat to be searched
   * @return Playable at the beat
   */
  IPlayable getPlayableAtBeat(int pitch, int beat);

  /**
   * Returns All the Playables in the Implementation
   *
   * @return all Playables
   */
  Collection<IPlayable> getAllPlayables();

  /**
   * Gets the Repeats from the Implementation
   *
   * @return repeats
   */
  Repeat getRepeats();

  /**
   * Sets the repeat in the implementation
   *
   * @param repeat repeat to be set
   */
  void setRepeats(Repeat repeat);

  /**
   * Determines whether this Implementation has an alternate ending
   *
   * @return whether or not it has one
   */
  boolean hasAlternateEnding();

  /**
   * Gets the implementation's alternate ending
   *
   * @return AlternateEnding
   * @throws IllegalArgumentException if there is no alternate ending
   */
  AlternateEnding getAlternateEnding();

  /**
   * Sets the alternate ending of this implementation
   *
   * @param boundRepeats the alternate ending(s)
   */
  void setAlternateEnding(List<BoundRepeat> boundRepeats);
}