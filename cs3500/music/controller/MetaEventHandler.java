package cs3500.music.controller;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.view.CompositeView;
import cs3500.music.view.ViewModel;
import cs3500.music.view.ViewModelWrapper;

/**
 * A MetaEventHandler
 */
class MetaEventHandler implements MetaEventListener {

  // Self Explanatory
  private CompositeView view;
  private ViewModel model;

  /**
   * Creates a MetaEvent Handler object
   *
   * @param view CV
   */
  MetaEventHandler(CompositeView view, MusicEditorModel model) {
    this.model = new ViewModelWrapper(model);
    this.view = view;
  }

  @Override
  public void meta(MetaMessage meta) {
    view.reinitialize(model);
  }
}
