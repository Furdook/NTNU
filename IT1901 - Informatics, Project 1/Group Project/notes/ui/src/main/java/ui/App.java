package ui;

import java.io.IOException;

import core.Note;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App.
 */
public class App extends Application {

    private static Scene scene;
    private static Note selectedNote;

    /**
     * Starts the application.
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {

        setScene();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Set scene for App.
     *
     * @return scene
     * @throws IOException if
     */
    public static Scene setScene() throws IOException {
        scene = new Scene(loadFXML("Notes"));
        return scene;
    }

    /**
     * Changes the scene to the one specified by the file.
     *
     * @param file the name of the file to change the scene to
     * @throws IOException
     */
    public static void changeScene(String file) throws IOException {
        scene.setRoot(loadFXML(file));
    }

    /**
     * Loads the .fxml file specified in param
     *
     * @param file the name of the file to load
     * @return the loaded file
     * @throws IOException
     */
    private static Parent loadFXML(String file) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(file + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Sets the selected note to the one specified in param (Clicked in the
     * view).
     *
     * @param note
     */
    protected static void setNote(Note note) {
        selectedNote = note;
    }

    /**
     * Returns the selected note.
     *
     * @return the selected note
     */
    protected static Note getNote() {
        return selectedNote;
    }

    /**
     * Launches the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}
