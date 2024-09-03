package core;

import java.io.File;
import java.util.List;

import core.persistence.IO;

/**
 * Class with a list of Note.
 */
public class NoteList {

    /**
     * Initializez the list of notes.
     */
    private static final List<Note> notes = initList();

    /**
     * Method to initialize the list of notes from the data.json file.
     *
     * @return List of notes
     */
    // In NoteList.java
    private static List<Note> initList() {
        List<Note> list = IO.getEntries();
        return list;
    }

    /**
     * Constructor for NoteList object. Uses a list to store notes.
     *
     * @param title The title of the note.
     * @param text The text of the note.
     * @param tags The tags of the note as a List of Strings.
     * @return id for note
     */
    public static int createNote(String title, String text, List<String> tags) {
        List<Note> currentNotes = IO.getEntries();
        int nextId = currentNotes.isEmpty() ? 1
                : currentNotes.get(currentNotes.size() - 1).getId() + 1;
        Note note = new Note(nextId, title, text, tags);
        notes.add(note);
        IO.writeFile(notes);
        return nextId;
    }

    /**
     * Method to get a list of all notes as a List.
     *
     * @return List of Note objects
     */
    public static List<Note> getNotes() {
        return notes;
    }

    /**
     * Method to delete a Note from the list and the file.
     *
     * @param id The id of the note to delete.
     */
    public static void deleteNote(int id) {
        // Delete the note from the file and remove it from the notes list
        IO.deleteEntry(id);
        notes.removeIf(note -> note.getId() == id);
    }

    /**
     * Set file path for data file.
     *
     * @param path Path and filename for file
     * @return File for data
     */
    public static File setFilePath(String path) {
        return IO.setFilePath(path);
    }
}
