package server;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import core.Note;
import core.NoteList;

@RestController
@CrossOrigin
public class ServerController {

    /**
     * This method is for testing the server.
     * 
     * @return string "Hello World!"
     */
    @GetMapping(path = "/test")
    public String test() {
        return "Hello, World!";
    }

    /**
     * This method is called when the client sends a GET request to /getNotes.
     *
     * @return Array of notes
     */
    @GetMapping(path = "/getNotes")
    public List<Note> getNotes() {
        return NoteList.getNotes();
    }

    /**
     * This method is called when the client sends a PUT request to /addNote.
     * Adds a new note to the list of notes.
     *
     * @param params the title, text, and tags of the note, separated by &
     * @return id to created note
     */
    @PutMapping(path = "/addNote")
    public int addNote(@RequestBody String params) {
        if (params.startsWith("{") && params.endsWith("}")) {
            // Json as string received: "{\"noteData\":\"New Note 1&&\"}"
            // Remove unnecessary characters
            // (Unable to send string into axios.put() in react frontend)
            int start = params.indexOf(":") + 2;
            params = params.substring(start, params.length() - 2);
        }
        String[] parts = params.split("&", -1);
        return NoteList.createNote(parts[0], parts[1],
                List.of(parts[2].split(",", 0)));
    }

    /**
     * This emthod is called when the client sends a POST request to /editNote.
     * Edits an existing note.
     *
     * @param id     existing note's id
     * @param params the new title, text, and tags of the note, separated by &
     */
    @PostMapping(path = "/editNote/{id}")
    public void editNote(@PathVariable final int id,
            @RequestBody String params) {
        if (params.startsWith("{") && params.endsWith("}")) {
            // Json as string received:
            // "{\"noteData\":\"New Note 1& context&tags\"}"
            // Remove unnecessary characters
            int start = params.indexOf(":") + 2;
            params = params.substring(start, params.length() - 2);
        }

        String[] parts = params.split("&", -1);
        NoteList.getNotes()
                .stream()
                .filter(note -> note.getId() == id)
                .findFirst()
                .get()
                .editNote(id, parts[0], parts[1],
                        List.of(parts[2].split(",", 0)));
    }

    /**
     * This method is called when the client sends a DELETE request to
     * /deleteNote. Deletes an existing note.
     *
     * @param id existing note's id
     */
    @DeleteMapping(path = "/deleteNote/{id}")
    public void deleteNote(@PathVariable final int id) {
        NoteList.deleteNote(id);
    }

}
