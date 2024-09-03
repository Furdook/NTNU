package notes.core.persistence;

import core.persistence.IO;
import core.Note;
import java.io.File;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOTest {

    // Set filepath to testfile
    private static String filepath = "./testIO.json";
    File file = IO.setFilePath(filepath);

    @AfterEach
    public void removeTestFile(){
        file.delete();
    }

    @Test
    public void testIO(){
        // Add entry
        Note note = new Note(1, "IOTest", "noteText", List.of());
        List<Note> notes = List.of(note);
        IO.writeFile(notes);

        // Edit entry
        IO.editEntry(1, "newIOTitle", "newText", List.of("green"));
        IO.editEntry(1, null, null, null);

        // Check value
        notes = IO.getEntries();
        assertEquals("newIOTitle", notes.get(0).getTitle());
    }
}
