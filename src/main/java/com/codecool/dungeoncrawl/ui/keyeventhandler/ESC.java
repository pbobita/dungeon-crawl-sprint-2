package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.ui.elements.StatusPane;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ESC implements KeyHandler{
    public static final KeyCode ESC = KeyCode.ESCAPE;

    @Override
    public void perform(KeyEvent event, GameLogic logic) {
        if (event.getCode() == ESC) {
            Platform.exit();
        }
    }
}
