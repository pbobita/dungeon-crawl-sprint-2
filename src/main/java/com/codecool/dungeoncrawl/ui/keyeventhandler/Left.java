package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.ui.elements.StatusPane;
import javafx.scene.input.KeyCode;

public class Left extends MoveHandler {
    public static final KeyCode LEFT = KeyCode.LEFT;
    public static final int DX = -1;
    public static final int DY = 0;
    private StatusPane statusPane;

    public Left(StatusPane statusPane) {
        super(LEFT, DX, DY, statusPane);
    }
}
