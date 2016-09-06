package cs3500.music.view;

import cs3500.music.model.MusicEditorModel;

/**
 * Wrapper for MusicEditorModel
 */
public class ViewModelWrapper extends ViewModel {

  /**
   * Creates a ViewModel
   *
   * @param model MEM
   */
  public ViewModelWrapper(MusicEditorModel model) {
    super(model);
  }
}