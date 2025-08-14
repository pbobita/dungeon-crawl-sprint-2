package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.ui.elements.StatusPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class MoveHandler implements KeyHandler{
    private final KeyCode keyCode;
    private final int dx;
    private final int dy;
    private final StatusPane statusPane;

    public MoveHandler(KeyCode keyCode, int dx, int dy, StatusPane statusPane) {
        this.keyCode = keyCode;
        this.dx = dx;
        this.dy = dy;
        this.statusPane = statusPane;
    }

    @Override
    public void perform(KeyEvent event, GameLogic logic) {
        if (!event.getCode().equals(keyCode)) return;

        boolean isAdmin = statusPane.getNameValueLabel().getText().equals("admin");
        logic.movePlayer(dx, dy);
        logic.getMap().moveMonsters();
        logic.getMap().moveFollower();

    }

}
