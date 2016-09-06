package cs3500.music.controller;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.view.CompositeView;

/**
 * Implements the Controller Initializes everything, passes the views and models where they need to
 * go
 */
public class ControllerImpl implements Controller {

  // Self Explanatory
  private MusicEditorModel model;
  private CompositeView view;

  /**
   * Constructor for ControllerImpl Object
   * @param m MEM
   * @param view CV
   */
  public ControllerImpl(MusicEditorModel m, CompositeView view) {
    this.model = m;
    this.view = view;
  }

  @Override
  public void initialize() {
    KeyboardHandler kbh = new KeyboardHandler(model, view);
    kbh.initialize();
    MetaEventHandler meta = new MetaEventHandler(view, model);
    MouseHandler mh = new MouseHandler(model, view);
    view.getGuiView().addKeyListener(kbh);
    view.getGuiView().addMouseListener(mh);
    view.addMetaListener(meta);
    view.initialize();
  }
}
