package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * GUIView interface
 */
public interface GuiView extends View {

  /**
   * Initialize the GuiView to make it visible.
   */
  void initialize();

  /**
   * Adds KeyListener to this view
   *
   * @param listener keylistener
   */
  void addKeyListener(KeyListener listener);

  /**
   * Adds mouseListener to this view
   *
   * @param mouseListener mouselistener
   */
  void addMouseListener(MouseListener mouseListener);

  /**
   * Scroll the ScrollPane to in a direction by a certain number of units
   */
  void setScrollPos(boolean horiz, int scrollUnits);

}
