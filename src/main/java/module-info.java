module defalt.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens defalt.demo to javafx.fxml;
    exports defalt.demo;
    opens defalt.demo.controller to javafx.fxml;
    exports defalt.demo.controller;
}