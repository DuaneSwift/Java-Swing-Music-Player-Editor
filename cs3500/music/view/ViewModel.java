package cs3500.music.view;

import java.util.Collection;

import cs3500.music.model.AlternateEnding;
import cs3500.music.model.IPlayable;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Repeat;

/**
 * Interface that holds all methods that are relevant to a View
 */
public abstract class ViewModel {

  private final MusicEditorModel model;

  public ViewModel(MusicEditorModel model) {
    this.model = model;
  }

  /**
   * Getter for notes at a specific beat
   *
   * @param beat at which the notes need to be retrieved
   * @return {@code this}
   */
  public Collection<IPlayable> getPlayablesAtBeat(int beat) {
    return this.model.getPlayablesAtBeat(beat);
  }

  /**
   * Getter for the pitch number of the highest playable
   *
   * @return the pitch number of the highest playable
   */
  public IPlayable getHighestPlayable() {
    return this.model.getHighestPlayable();
  }

  /**
   * Getter for the pitch number of the lowest playable
   *
   * @return the pitch number of the lowest playable
   */
  public IPlayable getLowestPlayable() {
    return this.model.getLowestPlayable();
  }

  /**
   * Getter for the last Beat of the composition
   *
   * @return the last Beat as an Int.
   */
  public int getLastBeat() {
    return this.model.getLastBeat();
  }


  /**
   * Method to check if this beat is contained in the composition.
   *
   * @param beat as an Int.
   * @return true if the beat is contained in the composition, false otherwise.
   */
  public boolean containsBeat(int beat) {
    return this.model.containsBeat(beat);
  }

  /**
   * Getter method to retrieve the tempo of the composition from the model.
   *
   * @return the tempo as an Int.
   */
  public int getTempo() {
    return this.model.getTempo();
  }

  public ViewModel getComposition() {
    return new ViewModelWrapper(this.model.getComposition());
  }

  public boolean isEmpty() {
    return this.model.isEmpty();
  }

  public Collection<IPlayable> getAllPlayables() {
    return model.getAllPlayables();
  }

  public Repeat getRepeats() {
    return model.getRepeats();
  }

  public AlternateEnding getAlternateEnding() {
    return model.getAlternateEnding();
  }

  public boolean hasAlternateEnding() {
    return model.hasAlternateEnding();
  }
}