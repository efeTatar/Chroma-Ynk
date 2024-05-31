package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DrawingApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/DrawingAppView.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Cromat-Ynk");
        primaryStage.setScene(new Scene(root));

        primaryStage.getScene().getStylesheets().add(getClass().getResource("/com/example/demo1/style.css").toExternalForm());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
