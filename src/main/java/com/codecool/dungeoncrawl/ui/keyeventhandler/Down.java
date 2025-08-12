package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.ui.elements.StatusPane;
import javafx.scene.input.KeyCode;

public class Down extends MoveHandler {
    public static final KeyCode DOWN = KeyCode.DOWN;
    public static final int DX = 0;
    public static final int DY = 1;
    private StatusPane statusPane;

    public Down(StatusPane statusPane) {
        super(DOWN, DX, DY,statusPane);
    }
}
