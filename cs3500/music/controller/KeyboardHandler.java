package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.music.model.AlternateEnding;
import cs3500.music.model.BoundRepeat;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.SimpleRepeat;
import cs3500.music.view.CompositeView;
import cs3500.music.view.ViewModelWrapper;

/**
 * Handles all KeyBoard Events
 */
class KeyboardHandler implements KeyListener {

  // Self Explanatory
  private Map<Character, Runnable> typedKey = new HashMap<>();
  private Map<Integer, Runnable> pressedKey = new HashMap<>();
  private Map<Integer, Runnable> releasedKey = new HashMap<>();
  private MusicEditorModel model;
  private CompositeView view;

  // Keys to be set
  private int key1 = 0;
  private int key2 = 0;

  // List of Bound Repeats
  private List<BoundRepeat> listofaltend;

  /**
   * Constructs a Keyboard handler
   *
   * @param model MEM
   * @param view  CV
   */
  KeyboardHandler(MusicEditorModel model, CompositeView view) {
    super();
    this.model = model;
    this.view = view;
    listofaltend = new ArrayList<>();
    initialize();
  }

  /**
   * Sets the Key-Action Bindings
   */
  void initialize() {
    pressedKey.put(KeyEvent.VK_SPACE, () -> view.getMidiView().togglePlay());
    pressedKey.put(KeyEvent.VK_LEFT, () -> leftKeyPressed());
    pressedKey.put(KeyEvent.VK_RIGHT, () -> rightKeyPressed());
    pressedKey.put(KeyEvent.VK_UP, () -> upKeyPressed());
    pressedKey.put(KeyEvent.VK_DOWN, () -> downKeyPressed());
    pressedKey.put(KeyEvent.VK_HOME, () -> homeKeyPressed());
    pressedKey.put(KeyEvent.VK_END, () -> endKeyPressed());
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (typedKey.containsKey(e.getKeyChar())) {
      typedKey.get(e.getKeyChar()).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {

    if (e.isShiftDown()) {

      // SIMPLE REPEAT - SHIFT+NUMBER, creates instantly
      if ((e.getKeyCode() > 48) && (e.getKeyCode() < 58)) {
        model.setRepeats(new SimpleRepeat(e.getKeyCode() - 48));
        view.setRepeats(new SimpleRepeat(e.getKeyCode() - 48));
        view.reinitialize(new ViewModelWrapper(model));
      }

      // DELETES ANY REPEAT OR ALTERNATE ENDINGS CURRENTLY IN PROGRESS/MADE
      if (e.getKeyCode() == KeyEvent.VK_C) {
        model.setRepeats(new SimpleRepeat(0));
        model.setAlternateEnding(new ArrayList<>());
        view.setAlternateEnding(new AlternateEnding(new ArrayList<BoundRepeat>()));
        view.setRepeats(new SimpleRepeat(0));
        resetKeys();
        view.reinitialize(new ViewModelWrapper(model));
      }
    }

    // BOUND REPEAT - CTRL+A * 2 (only), CTRL+NUMBER to set iterations and create
    if (e.isControlDown()) {
      if (e.getKeyCode() == KeyEvent.VK_A) {
        setKeys();
      }
      if (keysAreSet()) {
        if ((e.getKeyCode() > 48) && (e.getKeyCode() < 58)) {
          model.setRepeats(new BoundRepeat(e.getKeyCode() - 48, (key1 / 20), (key2 / 20)));
          view.setRepeats(new BoundRepeat(e.getKeyCode() - 48, (key1 / 20), (key2 / 20)));
          view.setCurrentBeat(0);
          view.reinitialize(new ViewModelWrapper(model));
          resetKeys();
        }
      }
    }

    // ALTERNATE ENDINGS - ALT+A x 2 (at least), ALT+E to set
    if (e.isAltDown()) {
      if (e.getKeyCode() == KeyEvent.VK_E) {
        if (listofaltend.size() > 0) {
          AlternateEnding alternateEnding = new AlternateEnding(listofaltend);
          view.setAlternateEnding(alternateEnding);
          model.setAlternateEnding(listofaltend);
          view.setCurrentBeat(0);
          view.reinitialize(new ViewModelWrapper(model));
        }
      }
      if (e.getKeyCode() == KeyEvent.VK_A) {
        setKeys();
      }
      if (keysAreSet()) {
        listofaltend.add(new BoundRepeat(1, key1 / 20, key2 / 20));
        resetKeys();
      }
    }
    if (pressedKey.containsKey(e.getKeyCode())) {
      pressedKey.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (releasedKey.containsKey(e.getKeyCode())) {
      releasedKey.get(e.getKeyCode()).run();
    }
  }

  /**
   * Moves the redline backward if left key pressed
   */
  private void leftKeyPressed() {
    view.setCurrentBeat(view.getCurrentBeat() - 1);
    view.reinitialize(new ViewModelWrapper(model));
  }

  /**
   * Moves the redline forward if right key pressed
   */
  private void rightKeyPressed() {
    view.setCurrentBeat(view.getCurrentBeat() + 25);
    view.reinitialize(new ViewModelWrapper(model));
  }

  /**
   * Scrolls the screen up if upkey pressed
   */
  private void upKeyPressed() {
    view.setScrollPos(false, -10);
  }

  /**
   * Scrolls the screen down if downkey pressed
   */
  private void downKeyPressed() {
    view.setScrollPos(false, 10);
  }

  /**
   * Sets the redline back to the start if homekey pressed
   */
  private void homeKeyPressed() {
    view.setCurrentBeat(0);
    view.reinitialize(new ViewModelWrapper(model));
  }

  /**
   * Goes to the last beat if endkey pressed
   */
  private void endKeyPressed() {
    view.setCurrentBeat(model.getLastBeat() * 20);
    view.reinitialize(new ViewModelWrapper(model));
  }

  /**
   * Sets the Keys to their relative beats
   */
  private void setKeys() {
    if (key1 == 0) {
      key1 = view.getCurrentBeat();
    } else if (key2 == 0) {
      key2 = view.getCurrentBeat();
    }
  }

  /**
   * Resets the keys used to create the repeats back to 0
   */
  private void resetKeys() {
    key1 = 0;
    key2 = 0;
  }

  /**
   * Returns whether the Keys have been set
   *
   * @return if the keys are not equal to 0
   */
  private boolean keysAreSet() {
    return (key1 != 0) && (key2 != 0);
  }
}
