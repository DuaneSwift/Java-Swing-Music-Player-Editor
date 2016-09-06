package cs3500.music;

import java.io.FileReader;
import java.io.IOException;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.CompositeView;
import cs3500.music.view.View;
import cs3500.music.view.ViewFactory;
import cs3500.music.view.ViewModel;
import cs3500.music.view.ViewModelWrapper;

/**
 * Main Run Class
 */
public class MusicEditor {

  /**
   * Constructs a Model, and then sets up the view and the controller
   *
   * @param args the arguments dictating the type of view and the song file to be read
   */
  public static void main(String args[]) {
    CompositionBuilder<MusicEditorModel> builder =
            new MusicEditorModelImpl.MusicEditorModelBuilder();
    MusicEditorModel model;
    try {
      model = MusicReader.parseFile(new FileReader(args[1]), builder);
      ViewModel viewModel = new ViewModelWrapper(model);
      if (args[0].contains("composite")) {
        CompositeView view = new CompositeView(viewModel);
        Controller controller = new ControllerImpl(model, view);
        controller.initialize();
      } else {
        View view = ViewFactory.getView(args[0], viewModel);
        view.initialize();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
