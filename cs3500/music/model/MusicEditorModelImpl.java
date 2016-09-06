package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import cs3500.music.util.CompositionBuilder;

/**
 * Represents a Composition Object
 */
public final class MusicEditorModelImpl implements MusicEditorModel {

  //Given Composition
  private final TreeMap<Integer, Set<IPlayable>> composition = new TreeMap<>();

  // Default Tempo
  private int tempo = 120000;

  //Repeats
  private Repeat repeats;

  private List<BoundRepeat> alternateEnding;

  // Default constructor
  public MusicEditorModelImpl() {
    alternateEnding = new ArrayList<>();
  }

  /*******************************************************************************************/
  /*************************************IMPLEMENTS********************************************/
  /*******************************************************************************************/


  /**
   * Adds some playable to the MusicEditor
   *
   * @return {@code this}
   */
  @Override
  public MusicEditorModel addPlayable(IPlayable playable) {
    for (int i = playable.getStartBeat();
         i < (playable.getDuration() + playable.getStartBeat()); i++) {
      if (composition.containsKey(i)) {
        composition.get(i).add(playable);
      } else {
        HashSet<IPlayable> newCollectionOfPlayable = new HashSet<>();
        newCollectionOfPlayable.add(playable);
        composition.put(i, newCollectionOfPlayable);
      }
    }
    return this;
  }

  /**
   * Removes some playable to the MusicEditor
   *
   * @return {@code this}
   */
  @Override
  public MusicEditorModel removePlayable(IPlayable playable) {
    for (int i = playable.getStartBeat();
         i < (playable.getStartBeat() + playable.getDuration()); i++) {
      composition.get(i).remove(playable);
    }
    return this;
  }

  /**
   * Returns the playable at a particular beat in the composition
   *
   * @return Playable
   */
  @Override
  public Collection<IPlayable> getPlayablesAtBeat(int beat) {
    return composition.get(beat);
  }

  /**
   * Returns the highest Playable in the composition
   *
   * @return Highest Playable
   */
  @Override
  public IPlayable getHighestPlayable() {
    // Dummy Initialization, Equivalent to no Playable
    IPlayable currentHighest = new Note(Note.NOTENAME.C, Note.NOTEOCTAVE.ZERO, 0, 1, 0);
    int currentHighestPitch = 0;
    for (int i = 0; i < composition.lastKey(); i++) {
      if (composition.containsKey(i)) {
        Collection<IPlayable> c = composition.get(i);
        for (IPlayable p : c) {
          if (p.getPitch() > currentHighestPitch) {
            currentHighestPitch = p.getPitch();
            currentHighest = p;
          }
        }
      }
    }
    return currentHighest;
  }

  /**
   * Returns the lowest Playable in the composition
   *
   * @return Lowest Playable
   */
  @Override
  public IPlayable getLowestPlayable() {
    // Dummy Initialization, Equivalent to no Playable
    IPlayable currentLowest = new Note(Note.NOTENAME.C, Note.NOTEOCTAVE.NINE, 0, 1, 0);
    int currentLowestPitch = 128;
    for (int i = 0; i < composition.lastKey(); i++) {
      if (composition.containsKey(i)) {
        Collection<IPlayable> c = composition.get(i);
        for (IPlayable p : c) {
          if (p.getPitch() < currentLowestPitch) {
            currentLowestPitch = p.getPitch();
            currentLowest = p;
          }
        }
      }
    }
    return currentLowest;
  }

  /**
   * Returns the final Playable in the composition
   *
   * @return Last Playable
   */
  @Override
  public Collection<IPlayable> getFinalPlayables() {
    int lengthOfLongestPlayable = 0;
    Collection<IPlayable> playableCollection = new ArrayList<>();
    for (IPlayable playable : composition.get(composition.lastKey())) {
      if (playable.getDuration() > lengthOfLongestPlayable) {
        playableCollection.clear();
        lengthOfLongestPlayable = playable.getDuration();
        playableCollection.add(playable);
      }
      if (playable.getDuration() == lengthOfLongestPlayable) {
        playableCollection.add(playable);
      }
    }
    return playableCollection;
  }

  @Override
  public MusicEditorModel addSimultaneous(MusicEditorModel newcomposition) {
    for (int i = 0; i < newcomposition.getComposition().getLastBeat(); i++) {
      if (newcomposition.getComposition().containsBeat(i)) {
        for (IPlayable p : newcomposition.getComposition().getPlayablesAtBeat(i)) {
          this.addPlayable(p);
        }
      }
    }
    return this;
  }

  @Override
  public MusicEditorModel addConsecutive(MusicEditorModel newcomposition) {
    int currentLast = this.composition.lastKey() + 1;
    for (int i = 0; i < newcomposition.getLastBeat(); i++) {
      if (newcomposition.getComposition().containsBeat(i)) {
        for (IPlayable p : newcomposition.getPlayablesAtBeat(i)) {
          Playable newPlayable = new Note(
                  p.getStartBeat() + currentLast, p.getDuration() + p.getStartBeat() + currentLast,
                  p.getInstrument(), p.getPitch() + 12, p.getVolume());
          this.addPlayable(newPlayable);
        }
      }
    }
    return this;
  }

  /**
   * Returns the final beat of the composition
   *
   * @return int final beat
   */
  @Override
  public int getLastBeat() {
    return composition.lastKey();
  }

  /**
   * Determines whether a composition contains a beat
   *
   * @param beat some given beat
   * @return whether or not the composition contains a particular beat number
   */
  @Override
  public boolean containsBeat(int beat) {
    return composition.containsKey(beat);
  }

  @Override
  public MusicEditorModel getComposition() {
    return this;
  }

  @Override
  public int getTempo() {
    return tempo;
  }

  /**
   * Is this implementation empty?
   *
   * @return whether or not it is empty
   */
  @Override
  public boolean isEmpty() {
    return composition.isEmpty();
  }

  /**
   * Sets the tempo of a this implementation
   *
   * @param tempo some tempo in milliseconds
   * @return {@code this}
   */
  @Override
  public MusicEditorModel setTempo(int tempo) {
    this.tempo = tempo;
    return this;
  }

  @Override
  public Note getPlayableAtBeat(int pitch, int beat) {
    for (IPlayable playable : this.getPlayablesAtBeat(beat)) {
      if (playable.equals(new Note(0, 1, 0, pitch, 1))) {
        return new Note(
                playable.getStartBeat(), (playable.getDuration() + playable.getStartBeat()),
                playable.getInstrument(), pitch, playable.getVolume());
      }
    }
    throw new IllegalArgumentException(String.format(
            "That Playable doesn't exist at beat %d", beat));
  }

  public Collection<IPlayable> getAllPlayables() {
    Collection<IPlayable> newCollection = new HashSet<>();
    for (int i = 0; i <= this.getLastBeat(); i++) {
      if (this.containsBeat(i)) {
        newCollection.addAll(this.getPlayablesAtBeat(i));
      }
    }
    return newCollection;
  }

  @Override
  public Repeat getRepeats() {
    return repeats;
  }

  @Override
  public void setRepeats(Repeat repeats) {
    this.repeats = repeats;
  }

  @Override
  public boolean hasAlternateEnding() {
    return alternateEnding.size() > 0;
  }

  @Override
  public AlternateEnding getAlternateEnding() {
    if (!hasAlternateEnding()) {
      throw new IllegalArgumentException("There is no alternate ending");
    } else {
      return new AlternateEnding(alternateEnding);
    }
  }

  @Override
  public void setAlternateEnding(List<BoundRepeat> boundRepeats) {
    alternateEnding = boundRepeats;
  }

  /**
   * BUILDER
   */
  public final static class MusicEditorModelBuilder implements CompositionBuilder {

    MusicEditorModel someComposition = new MusicEditorModelImpl();

    /**
     * Constructs an actual composition, given the notes that have been added
     *
     * @return The new composition
     */
    @Override
    public MusicEditorModel build() {
      return someComposition;
    }

    /**
     * Sets the tempo of the piece
     *
     * @param tempo The speed, in microseconds per beat
     * @return This builder
     */
    @Override
    public CompositionBuilder<MusicEditorModel> setTempo(int tempo) {
      someComposition.setTempo(tempo);
      return this;
    }

    /**
     * Adds a new note to the piece
     *
     * @param start      The start time of the note, in beats
     * @param end        The end time of the note, in beats
     * @param instrument The instrument number (to be interpreted by MIDI)
     * @param pitch      The pitch (in the range [0, 127], where 60 represents C4, the middle-C on
     *                   a piano)
     * @param volume     The volume (in the range [0, 127])
     */
    @Override
    public CompositionBuilder<MusicEditorModel> addNote(int start, int end,
                                                        int instrument, int pitch, int volume) {
      someComposition.addPlayable(new Note(start, end, instrument, pitch, volume));
      return this;
    }

    @Override
    public CompositionBuilder<MusicEditorModel> setRepeat1(int repeats) {
      Repeat repeat = new SimpleRepeat(repeats);
      someComposition.setRepeats(repeat);
      return this;
    }

    @Override
    public CompositionBuilder<MusicEditorModel> setRepeat2(int repeats, int start, int end) {
      Repeat repeat = new BoundRepeat(repeats, start, end);
      someComposition.setRepeats(repeat);
      return this;
    }

    @Override
    public CompositionBuilder setAlternateEndings(List list) {
      List<Integer> integers = list;
      int i = 0;
      List<BoundRepeat> boundRepeats = new ArrayList<>();
      while (i < integers.size()) {
        BoundRepeat boundRepeat = new BoundRepeat(1, integers.get(i), integers.get(i + 1));
        boundRepeats.add(boundRepeat);

        i = i + 2;
      }
      someComposition.setAlternateEnding(boundRepeats);
      return this;
    }
  }
}