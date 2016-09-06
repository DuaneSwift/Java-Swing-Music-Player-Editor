package cs3500.music.util;

import java.util.List;

/**
 * A builder of compositions.  Since we do not know in advance what the name of the main type is
 * for a model, we parameterize this builder interface by an unknown type.
 *
 * @param <T> The type of the constructed composition
 */
public interface CompositionBuilder<T> {
  /**
   * Constructs an actual composition, given the notes that have been added
   *
   * @return The new composition
   */
  T build();

  /**
   * Sets the tempo of the piece
   *
   * @param tempo The speed, in microseconds per beat
   * @return This builder
   */
  CompositionBuilder<T> setTempo(int tempo);

  /**
   * Adds a new note to the piece
   *
   * @param start      The start time of the note, in beats
   * @param end        The end time of the note, in beats
   * @param instrument The instrument number (to be interpreted by MIDI)
   * @param pitch      The pitch (in the range [0, 127], where 60 represents C4, the middle-C on a
   *                   piano)
   * @param volume     The volume (in the range [0, 127])
   * @return This builder
   */
  CompositionBuilder<T> addNote(int start, int end, int instrument, int pitch, int volume);

  /**
   * Sets a simple, whole song repeat
   *
   * @param repeats the number of times the song should be repeated
   * @return This builder
   */
  CompositionBuilder<T> setRepeat1(int repeats);

  /**
   * Sets a bound repeat
   *
   * @param repeats  the number of times this section should be repeated
   * @param startrep the starting beat of this repeat
   * @param endrep   the end beat of this repeat
   * @return This builder
   */
  CompositionBuilder<T> setRepeat2(int repeats, int startrep, int endrep);

  /**
   * Sets an alternate ending in a piece
   *
   * @param integers the even list of integers to be made into BoundRepeats
   * @return This builder
   */
  CompositionBuilder<T> setAlternateEndings(List<Integer> integers);
}
