package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cs3500.music.model.IPlayable;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.view.CompositeView;
import cs3500.music.view.ViewModelWrapper;

/**
 * Handles all events related to the Mouse and MouseListener.
 */
class MouseHandler implements MouseListener {

  // Self Explanatory
  private MusicEditorModel model;
  private CompositeView view;

  // X and Y coordinates
  private int yPressed;
  private int xPressed;

  // Playable being moved
  private IPlayable movingPlayable;

  /**
   * Constructor for Mouse Handler
   *
   * @param model For maximum editing functionality
   * @param view  for editing the composite view.
   */
  MouseHandler(MusicEditorModel model, CompositeView view) {
    super();
    this.model = model;
    this.view = view;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {

      // REMOVES PLAYABLE
      if (e.isControlDown()) {
        IPlayable playable = model.getPlayableAtBeat(
                pitchCalculator(e.getY()), beatCalc(e.getX()));
        model.removePlayable(playable);
        view.reinitialize(new ViewModelWrapper(model));
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    //Keeps track of the last pressed X and Y posns for other functions
    xPressed = e.getX();
    yPressed = e.getY();

    if (e.getButton() == MouseEvent.BUTTON1) {

      // REMOVES PLAYABLE THAT'S BEING MOVED
      if (e.isShiftDown()) {
        movingPlayable = model.getPlayableAtBeat(
                pitchCalculator(e.getY()), beatCalc(e.getX()));
        model.removePlayable(movingPlayable);
        view.reinitialize(new ViewModelWrapper(model));
      }
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {

      // ADDS PLAYABLE THAT WAS MOVED BACK TO MODEL IN NEW POSITION
      if (e.isShiftDown()) {
        model.addPlayable(
                new Note(beatCalc(e.getX()),
                        movingPlayable.getDuration() + beatCalc(e.getX()),
                        movingPlayable.getInstrument(),
                        pitchCalculator(e.getY()),
                        movingPlayable.getVolume()));
        view.reinitialize(new ViewModelWrapper(model));

        // ADDS PLAYABLE FROM WHERE DRAGGING STARTED TO THIS POINT OF RELEASE
        // SAME PITCH AND START BEAT AS START DRAG, END BEAT AT POINT OF RELEASE
      } else if (e.isAltDown()) {
        model.addPlayable(
                new Note(beatCalc(xPressed), beatCalc(e.getX()) + 1,
                        1, pitchCalculator(yPressed), 100));
        view.reinitialize(new ViewModelWrapper(model));
      }
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // NOT IMPLEMENTED
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // NOT IMPLEMENTED
  }

  /**
   * Calculates the pitch based on the cursor position on the view
   *
   * @param y the y coordinate
   * @return the MIDI pitch
   */
  private int pitchCalculator(int y) {
    int newY = y - 40;
    int highest = model.getHighestPlayable().getPitch();
    int boardpitch = newY / 20;
    return (highest - boardpitch) + 12;
  }

  /**
   * Calculates the beat based on the cursor position on the view
   *
   * @param x the x coordinate
   * @return the beat of the cursor click/position
   */
  private int beatCalc(int x) {
    int newX = x - 40;
    return newX / 20;
  }
}
