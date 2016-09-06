package cs3500.music.model;

/**
 * Represents a Note Object
 */
public class Note extends Playable {


  // All possible notes, without consideration for octave
  public enum NOTENAME {
    C(0),
    CSharp(1),
    DFlat(1),
    D(2),
    DSharp(3),
    EFlat(3),
    E(4),
    F(5),
    FSharp(6),
    GFlat(6),
    G(7),
    GSharp(8),
    AFlat(8),
    A(9),
    ASharp(10),
    BFlat(10),
    B(11);

    private int noteNumberWithinOctave;

    NOTENAME(int noteNumberWithinOctave) {
      this.noteNumberWithinOctave = noteNumberWithinOctave;
    }

    public int getNoteNumberWithinOctave() {
      return noteNumberWithinOctave;
    }
  }

  // All possible Octaves, without consideration for note names
  public enum NOTEOCTAVE {
    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
    EIGHT(8), NINE(9), TEN(10);

    private final int octaveValue;

    public int getOctaveValue() {
      return octaveValue;
    }

    /**
     * Constructs an Octave object.
     *
     * @param octaveValue is a String of the Pitch
     */
    NOTEOCTAVE(int octaveValue) {
      this.octaveValue = octaveValue;
    }
  }

  // This note's name
  private final NOTENAME notename;

  // This note's octave
  private final NOTEOCTAVE noteoctave;

  /**
   * Convenience, previously default Constructor - Now deprecated
   *
   * @param notename     a note name from a set of possible note names
   * @param noteoctave   a note octave from the set of possible octaves
   * @param startbeat    the starting beat of the note
   * @param noteduration the duration of the note
   * @param notevolume   the loudness of the note [0, 127] (MIDI)
   */
  public Note(NOTENAME notename, NOTEOCTAVE noteoctave,
              int startbeat, int noteduration, int notevolume) {
    super(noteduration, notevolume, startbeat, 1);
    this.notename = notename;
    this.noteoctave = noteoctave;
  }

  /**
   * Main, default constructor
   *
   * @param start      the start beat
   * @param end        the ending beat
   * @param instrument the MIDI instrument number
   * @param pitch      the MIDI pitch (- 12)
   * @param volume     the MIDI volume [0, 127]
   */
  public Note(int start, int end, int instrument, int pitch, int volume) {
    super((end - start), volume, start, instrument);
    int fixedpitch;
    if (pitch < 0) {
      fixedpitch = 12 + pitch;
    } else {
      fixedpitch = pitch;
    }
    this.notename = getNoteName(fixedpitch % 12);
    this.noteoctave = getNoteOctave(fixedpitch / 12);
  }

  /**
   * Returns what the current note is, based entirely on it's pitch
   *
   * @param notenumber given MIDI (-12) pitch
   * @return {@code NOTENAME}
   */
  private NOTENAME getNoteName(int notenumber) {
    NOTENAME somenotename = NOTENAME.C;
    for (NOTENAME nn : NOTENAME.values()) {
      if (nn.getNoteNumberWithinOctave() == notenumber) {
        somenotename = nn;
        return somenotename;
      }
    }
    return somenotename;
  }

  /**
   * Returns what octave the current note is
   *
   * @param noteoctave given MIDI (-12) pitch
   * @return {@code NOTEOCTAVE}
   */
  private NOTEOCTAVE getNoteOctave(int noteoctave) {
    NOTEOCTAVE somenoteoctave = NOTEOCTAVE.ZERO;
    for (NOTEOCTAVE no : NOTEOCTAVE.values()) {
      if (no.getOctaveValue() == noteoctave) {
        somenoteoctave = no;
        return somenoteoctave;
      }
    }
    return somenoteoctave;
  }


  /*******************************************************************************************/
  /**************************************GETTERS**********************************************/
  /*******************************************************************************************/

  /**
   * Returns the relative pitch number of a Playable
   *
   * @return int [0, undef)
   * @throws IllegalStateException if called on a pitch-less playable
   */
  @Override
  public int getPitch() {
    if (this.noteoctave.getOctaveValue() == 0) {
      return -(12 - notename.getNoteNumberWithinOctave());
    } else {
      return (this.noteoctave.getOctaveValue() * 12) + notename.getNoteNumberWithinOctave() - 12;
    }
  }

  /**
   * Returns the duration of a playable
   *
   * @return int [0, undef)
   */
  @Override
  public int getDuration() {
    return super.getDuration();
  }

  /**
   * Returns the volume of a playable
   *
   * @return int, [0, undef)
   */
  @Override
  public int getVolume() {
    return super.getVolume();
  }

  @Override
  public int compareTo(Playable playable) {
    if (this.getPitch() == playable.getPitch()) {
      return 0;
    } else if (this.getPitch() > playable.getPitch()) {
      return 1;
    } else {
      return -1;
    }
  }

  /**
   * Returns the octave of a Playable
   *
   * @return int, [0, undef)
   * @throws IllegalStateException if called on an octave-less playable
   */
  @Override
  public int getOctave() {
    return this.noteoctave.getOctaveValue() - 1;
  }

  /**
   * Returns the starting beat of a Playable
   *
   * @return int, [0, undef)
   */
  @Override
  public int getStartBeat() {
    return super.getStartBeat();
  }


  /*******************************************************************************************/
  /********************************************UTILS******************************************/
  /*******************************************************************************************/

  /**
   * Determines if two Notes are of the same Pitch and Octave Convenience method
   *
   * @param note2 Note object with Octave and Pitch
   * @return True is Octave and Pitch is the same, false otherwise
   */
  public boolean sameNote(Note note2) {
    return ((this.getPitch() == note2.getPitch()) && (this.getOctave() == note2.getOctave()));
  }

  /*******************************************************************************************/
  /**************************************OVERRIDDEN*******************************************/
  /*******************************************************************************************/

  /**
   * Overrides equals to compare instances of Playables.
   */
  @Override
  public boolean equals(Object note) {
    if (note instanceof Note) {
      Note n2 = (Note) note;
      return ((this.getPitch() == n2.getPitch()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.getPitch() + this.getOctave() * 46 + this.getVolume() + this.getStartBeat() / 5;
  }

  @Override
  public String toString() {
    if (this.getPitch() < 0) {
      return pitchToString(12 + getPitch());
    } else {
      return pitchToString(getPitch());
    }
  }

  /**
   * Converts some pitch number to it's equivalent String Independent from Note
   *
   * @param pitch some int [0, 128)
   * @return String of given pitch
   */
  public static String pitchToString(int pitch) {
    int abspitch;
    if (pitch < 0) {
      abspitch = 12 + pitch;
    } else {
      abspitch = pitch;
    }
    if ((abspitch % 12) == 0) {
      return "C" + pitch / 12;
    } else if ((abspitch % 12) == 1) {
      return "C#" + pitch / 12;
    } else if ((abspitch % 12) == 2) {
      return "D" + pitch / 12;
    } else if ((abspitch % 12) == 3) {
      return "D#" + pitch / 12;
    } else if ((abspitch % 12) == 4) {
      return "E" + pitch / 12;
    } else if ((abspitch % 12) == 5) {
      return "F" + pitch / 12;
    } else if ((abspitch % 12) == 6) {
      return "F#" + pitch / 12;
    } else if ((abspitch % 12) == 7) {
      return "G" + pitch / 12;
    } else if ((abspitch % 12) == 8) {
      return "G#" + pitch / 12;
    } else if ((abspitch % 12) == 9) {
      return "A" + pitch / 12;
    } else if ((abspitch % 12) == 10) {
      return "A#" + pitch / 12;
    } else if ((abspitch % 12) == 11) {
      return "B" + pitch / 12;
    } else {
      throw new IllegalStateException("There has been some error with the note number");
    }
  }
}