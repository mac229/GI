package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 700;

    public static void main(String[] args) throws IOException {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../main.fxml"));
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setTitle("Ocena atrakcyjności województw na podstawie danych GUS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
