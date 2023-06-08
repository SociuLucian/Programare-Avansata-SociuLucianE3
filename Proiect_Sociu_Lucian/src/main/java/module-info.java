module proiect.proiect_sociu_lucian {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.sql;

    opens proiect.proiect_sociu_lucian to javafx.fxml;
    exports proiect.proiect_sociu_lucian;
    exports proiect.proiect_sociu_lucian.model;
}