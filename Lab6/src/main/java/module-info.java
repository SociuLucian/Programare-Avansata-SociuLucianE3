module com.javalab.lab6.lab6java {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires javafx.swing;
    requires com.google.gson;

    opens com.javalab.lab6 to javafx.fxml;
    opens com.javalab.lab6.model to com.google.gson;
    exports com.javalab.lab6;
}