package ru.whoisthere;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Кто в зале!?");

        Parent layout = FXMLLoader.load(getClass().getResource("view/personsOverview.fxml"));

        Scene scene = new Scene (layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop(){
        System.exit(0);

    }
}
