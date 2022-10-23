package com.example.brickbreaker_acciojob;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainClass extends Application {

    static Navigation navigation;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Brick Breaker - Start Game");
        navigation = new Navigation(stage);

        navigation.load("StartPage.fxml");
    }

    public void StartButton(ActionEvent e) throws IOException {
        navigation.stage.setTitle("Brick Breaker Acciojob");
        navigation.load("GameScreen.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}