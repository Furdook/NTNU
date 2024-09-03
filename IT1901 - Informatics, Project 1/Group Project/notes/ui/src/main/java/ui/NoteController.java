package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import core.Note;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller for Note.fxml
 */
public class NoteController implements Initializable {

    @FXML
    private final Note note = App.getNote();

    @FXML
    private SplitMenuButton tags;

    @FXML
    private TextField noteTitle;

    @FXML
    private TextArea noteField;

    /**
     * Edits the selected note from the listview.
     *
     * @throws IOException
     */
    @FXML
    public void saveNote() throws IOException {
        String title = noteTitle.getText();
        String content = noteField.getText();
        List<String> tagsSelected = new ArrayList<>();

        tags.getItems().stream().forEach(item -> {
            if (((CheckMenuItem) item).isSelected()) {
                tagsSelected.add(item.getText());
            }
        });
        if (title.isBlank() || content.isBlank()) {
            return;
        }
        RestClient.editNote(note.getId(), title, content, tagsSelected);

        goBack();
    }

    /**
     * Deletes the selected note from the listview.
     *
     * @throws IOException
     */
    @FXML
    public void deleteNote() throws IOException {
        try {
            RestClient.deleteNote(note.getId());
            goBack();
        } catch (IOException e) {
            System.err.println("Error deleting note: " + e.getMessage());
        }
    }

    @FXML
    public void setNote() {
        noteTitle.setText(note.getTitle());
        noteField.setText(note.getText());
    }

    /**
     * Go back to the main screen. (ListView)
     *
     * @throws IOException if scene not found
     */
    @FXML
    public void goBack() throws IOException {
        try {
            App.changeScene("Notes");
        } catch (IOException e) {
            System.err.println("Error loading scene: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setNote();
    }
}
