package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.ui.elements.StatusPane;
import javafx.scene.input.KeyCode;

public class Right extends MoveHandler {
    public static final KeyCode RIGHT = KeyCode.RIGHT;
    public static final int DX = 1;
    public static final int DY = 0;
    private StatusPane statusPane;

    public Right(StatusPane statusPane) {
        super(RIGHT, DX, DY, statusPane);
    }
}
