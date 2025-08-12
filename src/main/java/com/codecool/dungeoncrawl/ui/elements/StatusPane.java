package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class StatusPane {
    public static final int RIGHT_PANEL_WIDTH = 200;
    public static final int RIGHT_PANEL_PADDING = 10;
    private GridPane ui;
    private Label healthTextLabel;
    private Label healthValueLabel;
    private Label maxHealthTextLabel;
    private Label maxHealthValueLabel;
    private Label attackPowerTextLabel;
    private Label attackPowerValueLabel;
    private Label inventoryTextLabel;
    private Label inventoryValueLabel;
    private Label perSignLabel;
    private Label nameTextLabel;
    private Label nameValueLabel;

    public StatusPane() {
        ui = new GridPane();
      
        nameTextLabel = new Label("Name: ");
        nameValueLabel = new Label();
        maxHealthTextLabel = new Label("Health: ");
        maxHealthValueLabel = new Label();
        attackPowerTextLabel = new Label("Damage: ");
        attackPowerValueLabel = new Label();
        inventoryTextLabel = new Label("Inventory: ");
        inventoryValueLabel = new Label();
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
        perSignLabel = new Label("/");
    }

    public BorderPane build() {

        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));
      
        ui.add(nameTextLabel, 0, 0);
        ui.add(nameValueLabel, 1, 0);

        ui.add(maxHealthTextLabel, 0, 1);
        ui.add(maxHealthValueLabel, 1, 1);
        ui.add(perSignLabel, 2, 1);
        ui.add(healthValueLabel, 3, 1);

        ui.add(attackPowerTextLabel, 0, 2);
        ui.add(attackPowerValueLabel, 1, 2);

        ui.add(inventoryTextLabel, 0, 3);
        ui.add(inventoryValueLabel, 1, 3);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(ui);
        return borderPane;
    }

    public void setMaxHealthValue(String text) {
        maxHealthValueLabel.setText(text);
    }
    public void setHealthValue(String text) { healthValueLabel.setText(text); }
    public void setNameValue(String name) {
        nameValueLabel.setText(name);
    }
    public void setAttackPowerValue(String text) { attackPowerValueLabel.setText(text); }
    public void setInventoryValue(String text) { inventoryValueLabel.setText(text); }
    public Label getNameValueLabel() {
        return nameValueLabel;
    }
}
