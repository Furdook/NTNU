module project {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics; // Needed for application to not throw warning on Stage class in App.java 

    opens project to javafx.fxml;
    exports project;
    exports project.modules to javafx.graphics;
}
