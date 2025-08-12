package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.awt.*;

public class MainStage {
    private final Canvas canvas;
    private final Scene scene;
    private final StatusPane statusPane;
    private InputHandler inputHandler;
    private StackPane root;

    public MainStage(Canvas canvas) {
        this.canvas = canvas;
        statusPane = new StatusPane();
        scene = setUpScene();
    }

    private Scene setUpScene() {
        BorderPane borderPane = statusPane.build();
        borderPane.setCenter(canvas);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        double width = screenSize.getWidth();

        double height = screenSize.getHeight();

        root = new StackPane(borderPane);
        root.setPrefSize(width, height);

        inputHandler = new InputHandler("Please enter your name:");
        StackPane.setAlignment(inputHandler, Pos.CENTER);
        root.getChildren().add(inputHandler);

        handleNameInput();

        return new Scene(root);
    }

    public void handleNameInput() {
        inputHandler.handleSubmit(name -> {
            statusPane.setNameValue(name);
            root.getChildren().remove(inputHandler);
        });
    }

    public Scene getScene() {
        return scene;
    }
    public void setHealthLabelText(String text) {
        this.statusPane.setHealthValue(text);
    }
    public void setAttackPowerLabelText(String text) { this.statusPane.setAttackPowerValue(text); }
    public void setInventoryLabelText(String text) { this.statusPane.setInventoryValue(text); }
    public void setMaxHealthLabelText(String text) { this.statusPane.setMaxHealthValue(text); }
    public StatusPane getStatusPane() {
        return statusPane;
    }
}
