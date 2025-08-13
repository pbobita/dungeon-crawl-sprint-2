package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MenuScreen extends VBox {
    private final String screenText;
    private final Button newGameButton;
    private final Button loadGameButton;
    private final Button exitButton;

    public MenuScreen(String text) {
        this.screenText = text;
        this.newGameButton = new Button("New game");
        this.loadGameButton = new Button("Load game");
        this.exitButton = new Button("Exit");

        createBox();
    }

    private void createBox() {
        Label text = new Label(screenText);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(text, newGameButton, loadGameButton, exitButton);
        styleButton(newGameButton);
        styleButton(loadGameButton);
        styleButton(exitButton);
        styleBox();
        styleLabel(text);
    }

    private void styleBox() {
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.9);");
    }

    private void styleButton(Button button) {
        button.setPrefSize(160, 40);
        VBox.setMargin(button, new Insets(10));
    }

    private void styleLabel(Label label) {
        label.setStyle("-fx-font-size: 30px;" + "-fx-text-fill: white;");
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(50));
    }

    public Button getNewGameButton() {
        return newGameButton;
    }

    public Button getLoadGameButton() {
        return loadGameButton;
    }

    public Button getExitButton() {
        return exitButton;
    }
}
