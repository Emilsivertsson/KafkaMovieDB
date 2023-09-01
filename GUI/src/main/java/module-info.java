module com.codeforpizza.gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.codeforpizza.gui to javafx.fxml;
    exports com.codeforpizza.gui;
}