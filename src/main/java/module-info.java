module defalt.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens defalt.demo to javafx.fxml;
    exports defalt.demo;
    opens defalt.demo.controller to javafx.fxml;
    exports defalt.demo.controller;
}