module com.example.mazemichaelversion {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens application to javafx.fxml;
    exports application;
}