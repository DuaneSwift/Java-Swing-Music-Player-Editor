package cs3500.music.Tests;

import org.junit.Test;

import java.io.IOException;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.model.Note;
import cs3500.music.model.Playable;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.ConsoleViewImpl;
import cs3500.music.view.ViewModel;
import cs3500.music.view.ViewModelWrapper;

import static org.junit.Assert.*;

/**
 * Created by duane on 3/23/2016.
 */
public class ConsoleViewImplTest {

  @Test
  public void testNovelAmazingComposition() throws IOException{
    Playable A4 = new Note(Note.NOTENAME.A, Note.NOTEOCTAVE.FOUR, 0, 4, 10);
    Playable E6 = new Note(Note.NOTENAME.E, Note.NOTEOCTAVE.SIX, 0, 7, 10);
    Playable GS3 = new Note(Note.NOTENAME.GSharp, Note.NOTEOCTAVE.THREE, 0, 4, 10);
    Playable C5 = new Note(Note.NOTENAME.C, Note.NOTEOCTAVE.FIVE, 1, 4, 10);
    Playable FS4 = new Note(Note.NOTENAME.FSharp, Note.NOTEOCTAVE.FOUR, 1, 6, 10);
    // Setting up Wrapper
    MusicEditorModel musicEditorModel = new MusicEditorModelImpl();
    ViewModel viewModel = new ViewModelWrapper(musicEditorModel);

    ConsoleView view = new ConsoleViewImpl(viewModel);
    musicEditorModel
            .addPlayable(A4)
            .addPlayable(GS3)
            .addPlayable(C5)
            .addPlayable(FS4);
    view.initialize();
    assertEquals(" G#2 A2A#2 B2 C3C#3 D3D#3 E3 F3F#3 G3G#3 A3A#3 B3 C4\n" +
            "0 X                                      X          \n" +
            "1 |                             X        |        X \n" +
            "2 |                             |        |        | \n" +
            "3 |                             |        |        | \n" +
            "4                               |                 | \n" +
            "5                               |                   \n", view.getViewLog());
  }

  @Test
  public void testMary() {

  }

}