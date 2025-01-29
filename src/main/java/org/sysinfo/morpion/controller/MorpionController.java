package org.sysinfo.morpion.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MorpionController {
    @FXML private GridPane gridPane;
    @FXML private Button b00, b01, b02, b10, b11, b12, b20, b21, b22;

    private byte[][] board = new byte[3][3];
    private boolean xTurn = true;
    private Button[][] buttons;

    @FXML
    public void initialize() {
        buttons = new Button[][] {
                {b00, b01, b02},
                {b10, b11, b12},
                {b20, b21, b22}
        };
    }

    @FXML
    public void handleButtonClick(javafx.event.ActionEvent event) {

    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fin de partie");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
