package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.skills.SpecialAbility;
import com.codecool.dungeoncrawl.logic.GameLogic;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Space implements KeyHandler{

    @Override
    public void perform(KeyEvent event, GameLogic logic) {
        if(event.getCode() == KeyCode.SPACE) {
            Player player = logic.getMap().getPlayer();
            SpecialAbility ability = player.getAbility();

            ability.use(player);
        }
    }
}
