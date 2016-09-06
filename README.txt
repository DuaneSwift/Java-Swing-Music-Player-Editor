Model:
Interfaces:

IPlayable
    -   The interface for all possible playables

MusicEditorModel
    -   The interface for the model for the MusicEditor - hosts all generalized functions that
        may be useful for manipulating the model/accessing the model.

    CHANGES:
        -   We changed methods from returning a Playable, to returning an IPlayable.
        -   We also stopped getComposition() from returning a TreeMap, and had it return a
            MusicEditorModelImpl


Abstract Classes:

Playable
    -   Represents all Playable things, implements IPlayable.
    -   Implements all functions in IPlayable, to be implemented by any Playable object that
        can be added to the composition


Classes:

Note
    -   Extends Playable, stores extra information like pitch.
    -   Enums represent the possible pitches, giving flexibility to enter the different names for
        the same pitches (eg. A flat, G Sharp)
    -   Overrides equals to allow full equality of notes.

    CHANGES:
        -   Notes now hold Octaves as Enums as Well
        -   There are now methods to determine what a note is, based on it's pitch
        -   There is now a Convenience Constructor to add a note, based on the given builder

MusicEditorModelImpl
    -   Implements MusicEditorModel, stores a composition as a TreeMap of Integers
        (representing beats to a Collection of Playables.

    -   Overrides toString, but doesn't do the processing for generating that string there, does
        it in CompositionView (NO LONGER DOES THIS)

    -   Now implements addConsecutive() and addSimultaneous() methods
    -   Now implements a builder to create a composition, for the music reader

CompositionView - REMOVED
    -   Done to keep the view generation and model seperate from each other
    -   Uses an array to temporarily store the pitches in a composition, for ease of
        writing to console
    -   Uses its own Pitch -> String function.

Views:

Interfaces:

View
    -   Holds all methods needed for all views; holds draw() method.

ConsoleView
    -   Holds all methods needed for Console Views; holds getViewLog();

MidiView
    -   Holds all methods needed for a MIDI View; none at the moment

GuiView
    -   Holds all methods needed for a GuiView; holds initialize();

Abstract Classes:

ViewModel
    -   Holds all methods needed for the Views.

Classes:

ViewModelWrapper
    -   Creates a wrapper for a MusicEditorModel.

ViewFactory
    -   Is a factory for the views, to be decided by the main program arguments

GuiViewImpl
    -   Implements the GuiView

MidiViewImpl
    -   Implements the MidiView, possibly an octave higher - the definition of Middle C was really
        Unclear, so I just went for C4 being middle C.

ConsoleViewImpl
    -   Implements the console view, replaces the previous implementation of the toString
        on MusicEditorModelImpl()

CompositeView
    -   Implements a view that contains both a GuiView and MidiView.
    -   Initializes them in this class.
    -   Synchronizes the beats between the MIDI and the GUI Views.

Controller:

Interfaces:
    -   Controller
        -   Holds the initialize method, not necessarily needed, but convenient

Abstract Classes:
    -   NONE

Classes:
    -   ControllerImpl
        -   Initializes all the events
        -   Adds the relevant mouse and key listeners to relevant views
    -   KeyboardHandler
        -   Implements KeyListener
        -   Has Key event actions
        -   Implements Key event actions
    -   MouseHandler
        -   Implements MouseListener
        -   Sets all the needed mouse click/key combinations
    -   MetaEventHandler
        -   Implements MetaEventListener
        -   Handles metaevents, i.e., literally reinitializes the view in the meta method