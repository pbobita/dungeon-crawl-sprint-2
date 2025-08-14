package com.codecool.dungeoncrawl.ui.elements;

import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.ui.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.util.List;

import java.awt.*;

public class MainStage {
    private final Canvas canvas;
    private final Scene scene;
    private final StatusPane statusPane;
    private InputHandler inputHandler;
    private StackPane root;
    private MenuScreen menuScreen;
    private final UI ui;
    private final GameLogic gameLogic;
    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public MainStage(Canvas canvas, GameLogic gameLogic, UI ui, Player player) {
        this.canvas = canvas;
        this.gameLogic = gameLogic;
        this.ui = ui;
        this.player = player;
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

        createStartScreen();
        handleStartNewGame();
        handleLoadGame();
        handleExit();

        return new Scene(root);
    }

    private void createStartScreen() {
        menuScreen = new MenuScreen("Welcome to Dungeon Crawler");
        StackPane.setAlignment(menuScreen, Pos.CENTER);
        root.getChildren().add(menuScreen);
    }

    public void handleGameOverScreen() {
        menuScreen = new MenuScreen("Game Over");
        StackPane.setAlignment(menuScreen, Pos.CENTER);
        root.getChildren().add(menuScreen);

        handleRestartGame();
        handleLoadGame();
        handleExit();
    }

    public void handleRestartGame() {
        gameLogic.startNewGame();
        player = gameLogic.getMap().getPlayer();

        StatusPane sp = getStatusPane();
        sp.setHealthValue(String.valueOf(player.getHealth()));
        sp.setAttackPowerValue(String.valueOf(player.getAttackPower()));
        sp.setMaxHealthValue(String.valueOf(player.getMaxHealth()));
        sp.setNameValue(player.getName());

        ui.refresh();
        handleStartNewGame();
    }

    public void handleStartNewGame() {
        menuScreen.getNewGameButton().setOnAction(event -> {
            root.getChildren().remove(menuScreen);
            inputHandler = new InputHandler("Please enter your name:");
            StackPane.setAlignment(inputHandler, Pos.CENTER);
            root.getChildren().add(inputHandler);
            handleNameInput();
        });
    }

    public void handleLoadGame() {
        menuScreen.getLoadGameButton().setOnAction(event -> {
            root.getChildren().remove(menuScreen);
            ui.loadPlayerAndRefresh();
        });
    }

    public void handleExit() {
        menuScreen.getExitButton().setOnAction(event -> System.exit(0));
    }

    public void handleNameInput() {
        inputHandler.getSubmitButton().setOnAction(event -> {
            String name = inputHandler.getInput().getText();
            submitName(name);
        });

        inputHandler.getInput().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String name = inputHandler.getInput().getText();
                submitName(name);
            }
        });
    }

    private void submitName(String name) {
        if (!name.isEmpty()) {
            statusPane.setNameValue(name);
            root.getChildren().remove(inputHandler);
            inputHandler.getInput().clear();
            gameLogic.getMap().getPlayer().setName(name);
        }
    }

    public Scene getScene() {
        return scene;
    }
    public void setHealthLabelText(String text) {
        this.statusPane.setHealthValue(text);
    }
    public void setAttackPowerLabelText(String text) { this.statusPane.setAttackPowerValue(text); }
    public void setInventoryLabelText(List<String> list) { this.statusPane.setInventoryItems(list); }
    public void setMaxHealthLabelText(String text) { this.statusPane.setMaxHealthValue(text); }
    public void setNameValueLabel(String text) { this.statusPane.setNameValue(text); }
    public StatusPane getStatusPane() {
        return statusPane;
    }

    public UI getUi() {
        return ui;
    }
}
