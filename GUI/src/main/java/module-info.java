module com.codeforpizza.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;

    opens com.codeforpizza.gui to javafx.fxml;
    exports com.codeforpizza.gui;
}