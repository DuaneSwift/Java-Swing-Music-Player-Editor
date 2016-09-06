package cs3500.music.view;

import java.nio.ByteBuffer;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import cs3500.music.model.IPlayable;


/**
 * Implementation of the MIDI View
 */
public class MidiViewImpl implements MidiView {

  private ViewModel model;
  private Sequencer sequencer;
  private Sequence sequence;
  private Track track;

  // Default volume
  private int volume = 100;

  // Used with TogglePlay function
  private boolean playingBool;

  // The tempo of the piece
  private int tempo;

  // The Offset Constant of the Note.
  // Fun: Change the value to change the key.
  //      12 is an exact octave above, adjust to suit
  private final int noteOffsetConstant = 12;

  /**
   * Constructs a MidiViewImpl object
   *
   * @param model VM
   */
  public MidiViewImpl(ViewModel model) {
    this.model = model;
    this.tempo = model.getTempo() * 4;
    Sequencer tempseqr = null;
    Sequence tempseq = null;
    try {
      tempseqr = MidiSystem.getSequencer();
      try {
        tempseq = new Sequence(Sequence.PPQ, 4);
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
      if (tempseqr == null) {
        System.out.println("Cannot get a sequencer");
        System.exit(0);
      }
      tempseqr.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    sequencer = tempseqr;
    sequence = tempseq;
    playingBool = false;
  }

  /**
   * Sends a given note to the midi receiver
   *
   * @param playable some playable item
   */
  private MidiEvent playNote(IPlayable playable) throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, playable.getInstrument(),
            playable.getPitch()
                    + noteOffsetConstant, volume);
    return new MidiEvent(start, playable.getStartBeat());
  }

  /**
   * Stops a note from being sent to the receiver
   *
   * @param playable some playable note.
   */
  private MidiEvent stopNote(IPlayable playable) throws InvalidMidiDataException {
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, playable.getInstrument(),
            playable.getPitch()
                    + noteOffsetConstant, volume);
    return new MidiEvent(stop, playable.getStartBeat() + playable.getDuration());
  }

  @Override
  public void initialize() {
    try {
      createMIDITrack();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void reinitialize(ViewModel model) {
    this.model = model;
    initialize();
  }

  /**
   * Creates a MIDI track to be played, based on the model Deletes any old tracks that may be
   * there
   */
  private void createMIDITrack() throws InvalidMidiDataException {
    sequence.deleteTrack(track);
    track = sequence.createTrack();
    for (int i = 0; i <= model.getLastBeat(); i++) {
      if (model.containsBeat(i)) {
        for (IPlayable p : model.getPlayablesAtBeat(i)) {
          if (i == p.getStartBeat()) {
            try {
              track.add(this.playNote(p));
              track.add(this.stopNote(p));
            } catch (InvalidMidiDataException e) {
              e.printStackTrace();
            }
          }
        }
      }
      for (int j = 0; j < model.getLastBeat(); j++) {
        byte[] bytes = ByteBuffer.allocate(4).putInt(j).array();
        MidiEvent midiEvent = new MidiEvent(
                new MetaMessage(1, ByteBuffer.allocate(4).putInt(j).array(),
                        bytes.length), j);
        track.add(midiEvent);
      }
    }
  }

  @Override
  public void togglePlay() {
    if (playingBool) {
      pause();
    } else {
      play();
    }
  }

  @Override
  public void addMetaListener(MetaEventListener metaListener) {
    this.sequencer.addMetaEventListener(metaListener);
  }

  @Override
  public void setCurrentBeat(int beat) {
    sequencer.setMicrosecondPosition(beat * 6000);
  }

  @Override
  public int getCurrentBeat() {
    return Math.toIntExact(sequencer.getMicrosecondPosition()) / 6000;
  }

  /**
   * Starts playing the MIDI/Sets the sequence
   */
  private void play() {
    try {
      sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    sequencer.setTempoInMPQ(tempo);
    sequencer.start();
    playingBool = true;
  }

  /**
   * Pauses the MIDI/Stops the sequencer
   */
  private void pause() {
    sequencer.stop();
    try {
      sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    sequencer.setTempoInMPQ(tempo);
    playingBool = false;
  }

  @Override
  public MidiView getView() {
    return this;
  }

}