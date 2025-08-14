package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Consumer;

public class LoadByNameScreen extends VBox {
    private final Label titleLabel;

    public LoadByNameScreen(List<String> playerNames, Consumer<String> onNameSelected) {
        this.titleLabel = new Label("Select a saved game:");
        createBox(playerNames, onNameSelected);
    }

    private void createBox(List<String> playerNames, Consumer<String> onNameSelected) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setPadding(new Insets(20));
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.9);");

        styleLabel(titleLabel);
        this.getChildren().add(titleLabel);

        for (String name : playerNames) {
            Button nameButton = new Button(name);
            styleButton(nameButton);
            nameButton.setOnAction(e -> onNameSelected.accept(name));
            this.getChildren().add(nameButton);
        }
    }

    private void styleButton(Button button) {
        button.setPrefSize(200, 40);
        VBox.setMargin(button, new Insets(5));
    }

    private void styleLabel(Label label) {
        label.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(20));
    }
}