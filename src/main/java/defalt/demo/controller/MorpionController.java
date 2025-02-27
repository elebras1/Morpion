package defalt.demo.controller;

import defalt.demo.network.ClientMorpion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MorpionController {
    @FXML private GridPane gridPane;
    @FXML private Button b00, b01, b02, b10, b11, b12, b20, b21, b22;

    private Button[][] buttons;
    private ClientMorpion client;

    @FXML
    public void initialize() {
        buttons = new Button[][] {
                {b00, b01, b02},
                {b10, b11, b12},
                {b20, b21, b22}
        };
        client = new ClientMorpion("localhost", 2048, buttons);
    }

    @FXML
    public void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == clickedButton) {
                    client.playTurn(i, j);
                    return;
                }
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fin de partie");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

