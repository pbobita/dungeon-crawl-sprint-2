package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.ui.elements.StatusPane;
import javafx.scene.input.KeyCode;

public class Up extends MoveHandler {
    public static final KeyCode UP = KeyCode.UP;
    public static final int DX = 0;
    public static final int DY = -1;
    private StatusPane statusPane;

    public Up(StatusPane statusPane) {
        super(UP, DX, DY, statusPane);
    }
}
