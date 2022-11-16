module com.baza.gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.baza.gui to javafx.fxml;
    exports com.baza.gui;
}