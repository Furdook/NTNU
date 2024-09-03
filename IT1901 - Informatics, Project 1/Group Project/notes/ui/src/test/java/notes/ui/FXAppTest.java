package notes.ui;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import core.NoteList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.App;

public class FXAppTest extends ApplicationTest {

    // Set filepath to testfile
    private static final String FILEPATH = "./testFXData.json";
    private static File file;

    @BeforeEach
    public void addTestNotes() {
        file = NoteList.setFilePath(FILEPATH);
        NoteList.createNote("testDelete", "note", List.of());
        NoteList.createNote("makeChanges", "note", List.of());
        NoteList.createNote("redTag", "note", List.of("Red"));
    }

    @AfterEach
    public void removeTestFile() {
        file.delete();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = App.setScene();
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testSaveButton() {
        clickOn("#noteTitle").write("TestSave");
        clickOn("#noteField").write("test");
        clickOn("#saveButton");
    }

    @Test
    public void makeChanges() {
        clickOn("#notesList");
        clickOn("makeChanges - note");
        doubleClickOn("#noteTitle").write("New Title");
        clickOn("#saveButton");
    }

    @Test
    public void testDeleteButton() {
        clickOn("#notesList");
        clickOn("testDelete - note");
        clickOn("#deleteButton");
    }

    @Test
    public void testFilterByTags() {
        // Add new Note with green tag
        clickOn("#tags.split-menu-button .arrow");
        clickOn("#green");
        clickOn("#noteTitle").write("GreenTag");
        clickOn("#noteField").write("note");
        clickOn("#saveButton");

        // Filter listview with green
        clickOn("#sort.split-menu-button .arrow");
        clickOn("#green");
    }
}
