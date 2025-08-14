package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.actors.Cat;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.GameLogic;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Unstuck implements KeyHandler {
    public static final KeyCode E = KeyCode.getKeyCode("E");
    @Override
    public void perform(KeyEvent event, GameLogic logic) {
        if (!event.getCode().equals(E)) return;

        Player player = logic.getMap().getPlayer();

        logic.getMap().getNPCs().stream()
                .filter(actor -> actor instanceof Cat)
                .map(actor -> (Cat) actor)
                .forEach(cat -> logic.catMoveOutOfWay(player,cat));
    }
}
