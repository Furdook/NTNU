package notes.core;

import core.Note;
import core.NoteList;
import core.persistence.IO;

import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class NoteListTest {

    // Change datafile to testfile
    private static String filepath = "./testNoteList.json";
    File file = IO.setFilePath(filepath);

    @AfterEach
    public void removeTestFile() {
        file.delete();
    }

    @Test
    public void testNoteList() {
        List<Note> notes = NoteList.getNotes();
        int initSize = notes.size();

        // Add notes to NoteList
        List<String> tags1 = List.of("Green", "Red");
        List<String> tags2 = List.of();

        NoteList.createNote("Note1", "Text1", tags1);
        NoteList.createNote("Note2", "Text2", tags2);

        notes = NoteList.getNotes();
        assertEquals(initSize + 2, notes.size());

        // Delete note from NoteList
        int noteId = notes.get(0).getId();
        NoteList.deleteNote(noteId);
        notes = NoteList.getNotes();
        assertEquals(initSize + 1, notes.size());
    }
}
