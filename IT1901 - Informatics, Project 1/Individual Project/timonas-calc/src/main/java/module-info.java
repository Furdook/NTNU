module timonas.calc {
    requires javafx.controls;
    requires javafx.fxml;

    opens timonas.calc to javafx.graphics, javafx.fxml;
}
