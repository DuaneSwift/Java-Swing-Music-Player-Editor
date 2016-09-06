package cs3500.music.tests;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Tests that MIDI correctly receives the correct notes to play, in the correct order
 */
public class MidiViewImplTest {
//  // Setting Up Midi
//  MockMidiSynthesizer synthesizer = new MockMidiSynthesizer();
//  MidiViewImpl midiView = new MidiViewImpl(synthesizer);
//
//  /**
//   * Tests if the correct notes are played from my own novel and wonderful composition of 5 notes
//   */
//  @Test
//  public void testMidiCorrectNotes() throws IOException {
//    Playable A4 = new Note(Note.NOTENAME.A, Note.NOTEOCTAVE.FOUR, 0, 4, 10);
//    Playable E6 = new Note(Note.NOTENAME.E, Note.NOTEOCTAVE.SIX, 0, 7, 10);
//    Playable GS3 = new Note(Note.NOTENAME.GSharp, Note.NOTEOCTAVE.THREE, 0, 4, 10);
//    Playable C5 = new Note(Note.NOTENAME.C, Note.NOTEOCTAVE.FIVE, 1, 4, 10);
//    Playable FS4 = new Note(Note.NOTENAME.FSharp, Note.NOTEOCTAVE.FOUR, 1, 6, 10);
//    // Setting up Wrapper
//    MusicEditorModel musicEditorModel = new MusicEditorModelImpl();
//    ViewModel viewModel = new ViewModelWrapper(musicEditorModel);
//    musicEditorModel
//            .addPlayable(A4)
//            .addPlayable(E6)
//            .addPlayable(GS3)
//            .addPlayable(C5)
//            .addPlayable(FS4);
//    midiView.draw(viewModel);
//    assertEquals("Pitch Number: 57\n" +
//            "Note Volume: 10\n" +
//            "\n" +
//            "Pitch Number: 76\n" +
//            "Note Volume: 10\n" +
//            "\n" +
//            "Pitch Number: 44\n" +
//            "Note Volume: 10\n" +
//            "\n" +
//            "Pitch Number: 60\n" +
//            "Note Volume: 10\n" +
//            "\n" +
//            "Pitch Number: 54\n" +
//            "Note Volume: 10\n" +
//            "\n", synthesizer.getNoteLog());
//  }
//
//  /**
//   * Tests that the correct notes are played from the text file Mary-Little-Lamb.txt
//   *
//   * @throws IOException if there is no text file named that
//   */
//  @Test
//  public void testMaryHADaLittleLamb() throws IOException {
//    Readable r = new FileReader("mary-little-lamb.txt");
//    CompositionBuilder<MusicEditorModel> builder =
//            new MusicEditorModelImpl.MusicEditorModelBuilder();
//    MusicEditorModel m = MusicReader.parseFile(r, builder);
//    ViewModel vm = new ViewModelWrapper(m);
//    midiView.draw(vm);
//    assertEquals("Pitch Number: 64\n" +
//            "Note Volume: 72\n" +
//            "\n" +
//            "Pitch Number: 55\n" +
//            "Note Volume: 70\n" +
//            "\n" +
//            "Pitch Number: 62\n" +
//            "Note Volume: 72\n" +
//            "\n" +
//            "Pitch Number: 60\n" +
//            "Note Volume: 71\n" +
//            "\n" +
//            "Pitch Number: 62\n" +
//            "Note Volume: 79\n" +
//            "\n" +
//            "Pitch Number: 55\n" +
//            "Note Volume: 79\n" +
//            "\n" +
//            "Pitch Number: 64\n" +
//            "Note Volume: 85\n" +
//            "\n" +
//            "Pitch Number: 64\n" +
//            "Note Volume: 78\n" +
//            "\n" +
//            "Pitch Number: 64\n" +
//            "Note Volume: 74\n" +
//            "\n" +
//            "Pitch Number: 55\n" +
//            "Note Volume: 77\n" +
//            "\n" +
//            "Pitch Number: 62\n" +
//            "Note Volume: 75\n" +
//            "\n" +
//            "Pitch Number: 62\n" +
//            "Note Volume: 77\n" +
//            "\n" +
//            "Pitch Number: 62\n" +
//            "Note Volume: 75\n" +
//            "\n" +
//            "Pitch Number: 55\n" +
//            "Note Volume: 79\n" +
//            "\n" +
//            "Pitch Number: 64\n" +
//            "Note Volume: 82\n" +
//            "\n" +
//            "Pitch Number: 67\n" +
//            "Note Volume: 84\n" +
//            "\n" +
//            "Pitch Number: 67\n" +
//            "Note Volume: 75\n" +
//            "\n" +
//            "Pitch Number: 55\n" +
//            "Note Volume: 78\n" +
//            "\n" +
//            "Pitch Number: 64\n" +
//            "Note Volume: 73\n" +
//            "\n" +
//            "Pitch Number: 62\n" +
//            "Note Volume: 69\n" +
//            "\n" +
//            "Pitch Number: 60\n" +
//            "Note Volume: 71\n" +
//            "\n" +
//            "Pitch Number: 62\n" +
//            "Note Volume: 80\n" +
//            "\n" +
//            "Pitch Number: 55\n" +
//            "Note Volume: 79\n" +
//            "\n" +
//            "Pitch Number: 64\n" +
//            "Note Volume: 84\n" +
//            "\n" +
//            "Pitch Number: 64\n" +
//            "Note Volume: 76\n" +
//            "\n" +
//            "Pitch Number: 64\n" +
//            "Note Volume: 74\n" +
//            "\n" +
//            "Pitch Number: 64\n" +
//            "Note Volume: 77\n" +
//            "\n" +
//            "Pitch Number: 55\n" +
//            "Note Volume: 78\n" +
//            "\n" +
//            "Pitch Number: 62\n" +
//            "Note Volume: 75\n" +
//            "\n" +
//            "Pitch Number: 62\n" +
//            "Note Volume: 74\n" +
//            "\n" +
//            "Pitch Number: 64\n" +
//            "Note Volume: 81\n" +
//            "\n" +
//            "Pitch Number: 62\n" +
//            "Note Volume: 70\n" +
//            "\n" +
//            "Pitch Number: 52\n" +
//            "Note Volume: 72\n" +
//            "\n" +
//            "Pitch Number: 60\n" +
//            "Note Volume: 73\n" +
//            "\n", synthesizer.getNoteLog());
//  }
}