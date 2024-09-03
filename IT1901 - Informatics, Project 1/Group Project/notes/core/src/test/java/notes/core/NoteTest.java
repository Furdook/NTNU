package notes.core;

import core.Note;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NoteTest {

    @Test
    public void testNote() {
        int id = 1;
        String noteTitle = "Title1";
        String noteText = "Text1";
        List<String> noteTags = List.of("Green", "Red");

        Note note = new Note(id, noteTitle, noteText, noteTags);
        checkNoteValues(note, noteTitle, noteText, noteTags);

        // Edit note using editNote()
        noteTitle = "Title2";
        noteText = "Text2";
        noteTags = List.of("Red");

        note = note.editNote(note.getId(), noteTitle, noteText, noteTags);
        note = note.editNote(note.getId(), null, null, null);
        checkNoteValues(note, noteTitle, noteText, noteTags);

        // Edit note using set functions
        noteTitle = "Title3";
        noteText = "Text3";
        noteTags = List.of("Green");

        note.setTags(noteTags);
        note.setTitle(noteTitle);
        note.setText(noteText);
        checkNoteValues(note, noteTitle, noteText, noteTags);
    }

    private void checkNoteValues(Note note, String title, String text, List<String> expectedTags) {
        assertEquals(title, note.getTitle());
        assertEquals(text, note.getText());
        List<String> noteTags = note.getTags();
        for (int i = 0; i < noteTags.size(); i++) {
            assertEquals(expectedTags.get(i), noteTags.get(i));
        }
        assertEquals(title + " - " + text, note.toString());
    }
}
