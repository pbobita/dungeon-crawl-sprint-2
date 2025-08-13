package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class InputHandler extends VBox {
    private final TextField input;
    private final Button submitButton;

    public InputHandler(String labelText) {
        input = new TextField();
        Label label = new Label(labelText);
        this.submitButton = new Button("Submit");

        VBox innerBox = new VBox(10, label, input, submitButton);
        innerBox.setAlignment(Pos.CENTER);

        styleBox();
        this.getChildren().add(innerBox);
    }

    private void styleBox() {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); -fx-padding: 20; -fx-border-radius: 5;");
        this.setMaxWidth(300);
        this.setMaxHeight(150);
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public TextField getInput() {
        return input;
    }
}
