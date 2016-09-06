package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.sound.midi.MetaEventListener;

import cs3500.music.model.AlternateEnding;
import cs3500.music.model.BoundRepeat;
import cs3500.music.model.Repeat;

/**
 * A Composite View
 */
public class CompositeView implements GuiView {

  // Obvious/Self Explanatory
  private final GuiView guiView;
  private final MidiView midiView;
  private ViewModel model;

  //Repeat Object
  private Repeat rep;

  //Number of repeats
  private int repeats;

  // EndBeat for Repeats
  private int end;

  //Alternate Ending Count (down)
  private int altEndingCount = 0;

  // Alternate Ending Count (up)
  private int randomCount = 0;

  // Alternate Ending Skip Beat start
  private int startskip = 0;

  // Altrnate Ending Skip Beat Stop
  private int endskip = 0;

  // Alternate Ending skip boolean
  private boolean skipbool;

  // Does this model have an alternate ending?
  private boolean hasAlternateEnding;

  // If the model has an alternate ending, here it is.
  private AlternateEnding alternateEndings;

  /**
   * Creates a CompositeView object with
   *
   * @param model as a ViewModel
   */
  public CompositeView(ViewModel model) {
    guiView = new GuiViewImpl(model);
    midiView = new MidiViewImpl(model);
    this.model = model;
    rep = model.getRepeats();
    repeats = model.getRepeats().getIterations();
    if (model.hasAlternateEnding()) {
      this.altEndingCount = model.getAlternateEnding().numberOfRepeats();
      this.alternateEndings = model.getAlternateEnding();
    }
    hasAlternateEnding = model.hasAlternateEnding();
    skipbool = false;
  }

  /**
   * Initializes the view
   */
  @Override
  public void initialize() {
    guiView.initialize();
    midiView.initialize();
    guiView.setCurrentBeat(midiView.getCurrentBeat());
  }

  /**
   * Reinitializes the view
   */
  @Override
  public void reinitialize(ViewModel model) {
    this.model = model;
    guiView.setCurrentBeat(midiView.getCurrentBeat());
    guiView.reinitialize(model);
    midiView.reinitialize(model);
    if (hasAlternateEnding) {
      handleAlternateEndings();
    }
    if (repeats > 0) {
      handleRepeats();
    }
  }

  /**
   * Handles the background of alternate endings
   */
  private void handleAlternateEndings() {
    if (alternateEndings.getAlternateEnding(0).getEnd() != 0) {
      if (altEndingCount > 0) {
        BoundRepeat br = alternateEndings.getAlternateEnding(randomCount);
        if (startskip == 0) {
          startskip = (br.getStart() * 20);
        }
        endskip = (br.getEnd() * 20);
        if (!skipbool) {
          if ((midiView.getCurrentBeat() > endskip) &&
                  (endskip < model.getLastBeat() * 20)) { // Just incase stupid humans exist
            midiView.setCurrentBeat(0);
            guiView.setCurrentBeat(midiView.getCurrentBeat());
            skipbool = true;
          }
        } else {
          if (midiView.getCurrentBeat() > startskip) {
            midiView.setCurrentBeat(endskip);
            guiView.setCurrentBeat(midiView.getCurrentBeat());
            skipbool = false;
            randomCount++;
            altEndingCount--;
          }
        }
      }
    }
  }

  /**
   * Handles Repeats
   */
  private void handleRepeats() {
    if (rep.getEnd() == 0) {
      end = model.getLastBeat() * 20;
    } else {
      end = (rep.getEnd() * 20);
    }
    if (midiView.getCurrentBeat() > end) {
      midiView.setCurrentBeat((rep.getStart() * 20));
      guiView.setCurrentBeat(midiView.getCurrentBeat());
      repeats--;
    }
  }

  /**
   * Sets the Repeats after object creation Alt+A at whatever beats, Alt+Num to set loop number and
   * create
   */
  public void setRepeats(Repeat repeats) {
    this.rep = repeats;
    this.repeats = repeats.getIterations();
  }

  /**
   * Sets the AlternateEndings after object creation Alt+A at whatever beats, Press Alt+E to
   * create
   */
  public void setAlternateEnding(AlternateEnding alternateEnding) {
    this.hasAlternateEnding = alternateEnding.numberOfRepeats() > 0;
    this.alternateEndings = alternateEnding;
    this.altEndingCount = alternateEnding.numberOfRepeats();

  }

  /**
   * Control the position of the scroll pane in the gui view.
   *
   * @param horiz,     true or false for controlling the horizontal or vertical view.
   * @param scrollUnit number of units to scroll
   */
  public void setScrollPos(boolean horiz, int scrollUnit) {
    guiView.setScrollPos(horiz, scrollUnit);
  }


  /**
   * Retrieving object of view type interface.
   *
   * @return view
   */
  public View getView() {
    return this;
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    guiView.addKeyListener(listener);
  }

  @Override
  public void addMouseListener(MouseListener mouseListener) {
    guiView.addMouseListener(mouseListener);
  }

  public void addMetaListener(MetaEventListener metaListener) {
    midiView.addMetaListener(metaListener);
  }

  @Override
  public void setCurrentBeat(int beat) {
    midiView.setCurrentBeat(beat);
    guiView.setCurrentBeat(beat);
  }

  @Override
  public int getCurrentBeat() {
    return midiView.getCurrentBeat();
  }

  /**
   * Returns the GuiView
   *
   * @return guiview
   */
  public GuiView getGuiView() {
    return guiView;
  }

  /**
   * Returns MIDI view
   *
   * @return midiview
   */
  public MidiView getMidiView() {
    return midiView;
  }
}
