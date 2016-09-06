package cs3500.music.view;

import javax.sound.midi.MetaEventListener;

/**
 * Generalized view to have methods to be implemented by all MIDI Views. Done as a just-in-case.
 */
public interface MidiView extends View {

  /**
   * Toggles Play in this view
   */
  void togglePlay();

  /**
   * Adds a metaevent listener to this view
   */
  void addMetaListener(MetaEventListener metaListener);

  /**
   * Gets MidiView
   *
   * @return {@code this}
   */
  MidiView getView();

}
