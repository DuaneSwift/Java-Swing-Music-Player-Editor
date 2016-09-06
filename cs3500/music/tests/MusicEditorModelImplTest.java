package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.model.Note;
import cs3500.music.model.Playable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests all methods within the implementation of MusicEditorModel
 */
public class MusicEditorModelImplTest {

  Note A4 = new Note(Note.NOTENAME.A, Note.NOTEOCTAVE.FOUR, 0, 4, 10);
  Note E6 = new Note(Note.NOTENAME.E, Note.NOTEOCTAVE.SIX, 0, 7, 10);
  Note GS3 = new Note(Note.NOTENAME.GSharp, Note.NOTEOCTAVE.THREE, 0, 4, 10);
  Note C5 = new Note(Note.NOTENAME.C, Note.NOTEOCTAVE.FIVE, 1, 4, 10);
  Note FS4 = new Note(Note.NOTENAME.FSharp, Note.NOTEOCTAVE.FOUR, 1, 6, 10);

  Collection<Playable> comp = new ArrayList<>();

  @Test
  public void testAddPlayable() throws Exception {
    MusicEditorModelImpl composition = new MusicEditorModelImpl();
    composition.addPlayable(A4);
    assertEquals(A4, composition.getLowestPlayable());
    assertEquals(A4, composition.getHighestPlayable());
    composition.addPlayable(GS3);
    assertEquals(A4, composition.getHighestPlayable());
    assertEquals(GS3, composition.getLowestPlayable());
  }

  @Test
  public void testRemovePlayable() throws Exception {
    MusicEditorModelImpl composition = new MusicEditorModelImpl();
    composition.addPlayable(A4);
    assertEquals(A4, composition.getLowestPlayable());
    assertEquals(A4, composition.getHighestPlayable());
    composition.addPlayable(GS3);
    assertEquals(A4, composition.getHighestPlayable());
    assertEquals(GS3, composition.getLowestPlayable());
    composition.removePlayable(A4);
    assertEquals(GS3, composition.getHighestPlayable());
    assertEquals(GS3, composition.getLowestPlayable());
  }

  @Test
  public void testGetPlayablesAtBeat() throws Exception {
    MusicEditorModelImpl composition = new MusicEditorModelImpl();
    composition.addPlayable(A4);
    composition.addPlayable(C5);
    assertTrue(composition.getPlayablesAtBeat(0).contains(A4));
    assertFalse(composition.getPlayablesAtBeat(0).contains(C5));
    assertTrue(composition.getPlayablesAtBeat(1).contains(A4));
    assertTrue(composition.getPlayablesAtBeat(1).contains(C5));
  }

  @Test
  public void testGetHighestPlayable() throws Exception {
    MusicEditorModelImpl composition = new MusicEditorModelImpl();
    composition.addPlayable(A4);
    assertEquals(A4, composition.getHighestPlayable());
    composition.addPlayable(GS3);
    assertEquals(A4, composition.getHighestPlayable());
    composition.addPlayable(C5);
    assertEquals(C5, composition.getHighestPlayable());
  }

  @Test
  public void testGetLowestPlayable() throws Exception {
    MusicEditorModelImpl composition = new MusicEditorModelImpl();
    composition.addPlayable(A4);
    assertEquals(A4, composition.getLowestPlayable());
    composition.addPlayable(GS3);
    assertEquals(GS3, composition.getLowestPlayable());
  }

  @Test
  public void testToString() throws Exception {
    MusicEditorModelImpl composition = new MusicEditorModelImpl();
    composition.addPlayable(A4).addPlayable(GS3).addPlayable(C5).addPlayable(FS4);
    //composition.toString();
  }

  @Test
  public void testGetFinalPlayable() throws Exception {
    MusicEditorModelImpl composition = new MusicEditorModelImpl();
    composition.addPlayable(A4);
    composition.addPlayable(GS3);
    composition.addPlayable(C5);
    composition.addPlayable(FS4);
    assertTrue(composition.getFinalPlayables().contains(FS4));
  }

  @Test
  public void testGetLastBeat() throws Exception {
    MusicEditorModelImpl composition = new MusicEditorModelImpl();
    composition.addPlayable(A4);
    composition.addPlayable(GS3);
    composition.addPlayable(C5);
    composition.addPlayable(FS4);
    assertEquals(6, composition.getLastBeat());
  }

  @Test
  public void testContainsBeat() throws Exception {
    MusicEditorModelImpl composition = new MusicEditorModelImpl();
    composition.addPlayable(A4);
    composition.addPlayable(GS3);
    composition.addPlayable(C5);
    composition.addPlayable(FS4);
    assertTrue(composition.containsBeat(5));
    assertFalse(composition.containsBeat(7));
  }

  @Test
  public void testAddSimultaneous() throws Exception {
    MusicEditorModelImpl composition = new MusicEditorModelImpl();
    composition.addPlayable(A4);
    composition.addPlayable(GS3);
    composition.addPlayable(C5);

    MusicEditorModelImpl composition1 = new MusicEditorModelImpl();
    composition1.addPlayable(FS4);
    composition.addSimultaneous(composition1);
  }

  @Test
  public void testAddConsecutive() throws Exception {
    MusicEditorModelImpl composition = new MusicEditorModelImpl();
    composition.addPlayable(A4).addPlayable(GS3).addPlayable(C5).addPlayable(FS4);

    MusicEditorModelImpl composition1 = new MusicEditorModelImpl();
    composition1.addPlayable(A4).addPlayable(GS3).addPlayable(C5).addPlayable(E6);

    composition.addConsecutive(composition1);
  }
}