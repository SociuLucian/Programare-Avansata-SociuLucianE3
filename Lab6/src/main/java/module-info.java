module com.example.lab6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires javafx.base;
    requires javafx.graphics;
    requires java.desktop;

    opens com.example.lab6 to javafx.fxml;
    exports com.example.lab6;
}