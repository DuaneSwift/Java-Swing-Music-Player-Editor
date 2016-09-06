package cs3500.music.view;

/**
 * Generalized interface for all Views.
 */
public interface View {

  /**
   * Initializes the view
   */
  void initialize();

  /**
   * Reinitialize the view
   *
   * @param model a Given updated ViewModel
   */
  void reinitialize(ViewModel model);

  /**
   * Sets the current beat of the View
   *
   * @param beat beat to be set to
   */
  void setCurrentBeat(int beat);

  /**
   * Gets the current beat of the View
   *
   * @return current beat
   */
  int getCurrentBeat();

  /**
   * Returns the view.
   *
   * @return {@code this}
   */
  View getView();
}