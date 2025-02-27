package defalt.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MorpionApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MorpionApplication.class.getResource("Morpion_ihm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 320);
        //fxmlLoader.setController();
        stage.setTitle("Morpion");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}