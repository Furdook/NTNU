package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import core.Note;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller for Notes.fxml
 */
public class AppController implements Initializable {

    @FXML
    private TextField noteTitle;

    @FXML
    private TextArea noteField;

    @FXML
    private SplitMenuButton tags, sort;

    @FXML
    private ListView<String> notesList;
    private Note selectedNote;

    /**
     * Saves the note to the listview.
     */
    @FXML
    public void addNote() {
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
        RestClient.createNote(title, content, tagsSelected);

        clearFields();
        updateListView();
    }

    /**
     * Clears the textfields when notes are created or edited.
     */
    private void clearFields() {
        noteTitle.clear();
        noteField.clear();
    }

    /**
     * Updates the listview with the current notes after adding a new note or
     * sorting notes.
     */
    @FXML
    private void updateListView() {
        ObservableList<String> items = FXCollections.observableArrayList(
                sort.getItems().stream().filter(tag -> ((CheckMenuItem) tag)
                .isSelected()).toList().isEmpty()
                ? RestClient.getNotes().stream().map(note -> note.toString()).toList()
                : SortListView.sortNotes(sort.getItems().stream()
                        .filter(tag -> ((CheckMenuItem) tag).isSelected())
                        .map(tag -> tag.getText()).toList()));

        notesList.setItems(items);
    }

    /**
     * Initializes the controller class.
     *
     * @param location default value
     *
     * @param resources default value
     *
     * @throws RuntimeException if scene not found
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Populate the ListView with initial data
        updateListView();
        notesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        /*
         * Creates a listener for the listview. When a note is selected, updates
         * selectedNote.
         */
        notesList.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    RestClient.getNotes().stream().forEach(note -> {
                        if (note.toString().equals(newValue)) {
                            selectedNote = note;
                            App.setNote(selectedNote);
                            try {
                                changeScene("Note");
                            } catch (RuntimeException | IOException e) {
                                System.err.println(e);
                            }
                        }
                    });
                });
    }

    /**
     * Changes the scene to the one specified in param. In this case will be
     * switcing to a specified note If param is null navigate BACK to notes.fxml
     *
     * @param file the name of the file to change the scene to "note.fxml"
     * @throws IOException
     */
    private void changeScene(String file) throws IOException {
        if (file == null) {
            return;
        }
        App.changeScene(file);

    }
}
