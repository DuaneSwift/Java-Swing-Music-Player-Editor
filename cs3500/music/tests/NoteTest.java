package cs3500.music.Tests;

import org.junit.Test;

import cs3500.music.model.Note;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the implementation of Notes
 */
public class NoteTest {

  Note A0 = new Note(Note.NOTENAME.A, Note.NOTEOCTAVE.ZERO, 0, 4, 10);
  Note C4 = new Note(Note.NOTENAME.C, Note.NOTEOCTAVE.FOUR, 0, 4, 10);

  @Test
  public void testGetPitch() throws Exception {
    assertEquals(-3, A0.getPitch());
    assertEquals(36, C4.getPitch());
  }

  @Test
  public void testGetDuration() throws Exception {
    assertEquals(4, A0.getDuration());
  }

  @Test
  public void testGetVolume() throws Exception {
    assertEquals(10, C4.getVolume());
  }

  @Test
  public void testGetOctave() throws Exception {
    assertEquals(3, C4.getOctave());
  }

  @Test
  public void testRemove() throws Exception {

  }

  @Test
  public void testGetStartBeat() throws Exception {
    assertEquals(0, A0.getStartBeat());
  }

  @Test
  public void testEquals() throws Exception {
    assertFalse(A0.equals(new Note(Note.NOTENAME.A, Note.NOTEOCTAVE.ZERO, 1, 4, 10)));
    assertTrue((new Note(Note.NOTENAME.FSharp, Note.NOTEOCTAVE.ZERO, 0, 1, 0)
            .equals(new Note(Note.NOTENAME.GFlat, Note.NOTEOCTAVE.ZERO, 0, 1, 0))));
    assertTrue((new Note(Note.NOTENAME.GFlat, Note.NOTEOCTAVE.ZERO, 0, 1, 0)
            .equals(new Note(Note.NOTENAME.FSharp, Note.NOTEOCTAVE.ZERO, 0, 1, 0))));
  }

  @Test
  public void testToString() throws Exception {
    //assertEquals("A0", A0.toString());
    assertTrue(C4.toString().equals("C3"));
    assertEquals((new Note(Note.NOTENAME.C, Note.NOTEOCTAVE.ZERO, 0, 4, 10).toString()),
            "C-1");
    assertEquals("C#-1", (new Note(Note.NOTENAME.CSharp, Note.NOTEOCTAVE.ZERO, 0, 4, 10)
            .toString()));
    assertTrue((new Note(Note.NOTENAME.D, Note.NOTEOCTAVE.ZERO, 0, 4, 10).toString()
            .equals("D-1")));
    assertTrue((new Note(Note.NOTENAME.DSharp, Note.NOTEOCTAVE.ZERO, 0, 4, 10).toString()
            .equals("D#-1")));
    assertTrue((new Note(Note.NOTENAME.EFlat, Note.NOTEOCTAVE.ZERO, 0, 4, 10).toString()
            .equals("D#-1")));
    assertTrue((new Note(Note.NOTENAME.E, Note.NOTEOCTAVE.ZERO, 0, 4, 10).toString()
            .equals("E-1")));
  }
}