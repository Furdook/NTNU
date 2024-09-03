package core.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import core.Note;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for IO, saves to file.
 */
public class IO {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Path userPath = Path.of(System.getProperty("user.dir"));
    private static String dataPath = userPath + "data.json";
    private static File file;

    /**
     * Change filepath for data file, used for tests.
     *
     * @param path path to file
     * @return file
     */
    public static File setFilePath(String path) {
        IO.dataPath = path;
        file = new File(path);
        return file;
    }

    /**
     * Creates the data.json file if it does not exist
     *
     * @exception prints stacktrace to console if error occurs
     */
    private static void createFile() {
        file = new File(dataPath);

        if (file.exists()) {
            return;
        }

        try {
            List<Note> emptyList = new ArrayList<>();
            objectMapper.writeValue(file, emptyList);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Reads the data.json file and returns a list of Note objects
     *
     * @return List of Note objects
     */
    protected static List<Note> readFile() {
        if (file == null || !file.exists()) {
            createFile();
        }

        try {
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON array to list of notes
            List<Note> notes = mapper.readValue(file, new TypeReference<List<Note>>() {
            });
            return notes;
        } catch (IOException e) {
            // Print the exception details for debugging
            System.err.println("Error: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list in case of an error
        }
    }

    /**
     * Writes a list of Note objects to the data.json file
     *
     * @param notes the list of Note objects to write to the file
     */
    public static void writeFile(List<Note> notes) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(file, notes);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Function to get all entries from data.json
     *
     * @return List of Note objects containing data
     */
    public static List<Note> getEntries() {
        return readFile();
    }

    /**
     * Function to delete a note entry from the data.json file Simply passes if
     * the note ID does not exist
     *
     * @param id the id of the note to delete
     */
    public static void deleteEntry(int id) {
        List<Note> currentNotes = getEntries();
        currentNotes.removeIf(note -> note.getId() == id);
        writeFile(currentNotes);
    }

    /**
     * Function to edit a note stored in data.json, looks up note based on note
     * ID Leave params empty to not affect the prior values
     *
     * @param id the id of the note to edit
     * @param title the new title of the note
     * @param content the new content of the note
     * @param tags the new tags of the note
     */
    public static void editEntry(int id, String title, String content, List<String> tags) {
        List<Note> currentNotes = getEntries();
        for (Note note : currentNotes) {
            if (note.getId() == id) {
                if (title != null) {
                    note.setTitle(title);
                }
                if (content != null) {
                    note.setText(content);
                }
                if (tags != null) {
                    note.setTags(tags);
                }
                writeFile(currentNotes);
                break;
            }
        }
    }
}
