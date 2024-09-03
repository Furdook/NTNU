module notes.ui {
    requires notes.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    opens ui to javafx.graphics, javafx.fxml, javafx.controls, notes.core, com.fasterxml.jackson.databind, com.fasterxml.jackson.core, com.fasterxml.jackson.annotation;
}
