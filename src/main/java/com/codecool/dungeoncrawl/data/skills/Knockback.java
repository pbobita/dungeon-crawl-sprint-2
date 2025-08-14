package com.codecool.dungeoncrawl.data.skills;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Monster;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Knockback extends SpecialAbility {

    public Knockback() {
        super("Knockback", 5, 2, 0);
    }

    @Override
    public void use(Actor user) {
        if(user instanceof Monster) {
            Cell monsterCell = user.getCell();
            GameMap map = monsterCell.getGameMap();
            Cell playerCell = map.getPlayer().getCell();

            if(user.getHealth() <= 0.5 * user.getMaxHealth()) {
                int[][] offsets = {
                        {2, 0}, {-2, 0},
                        {0, 2}, {0, -2},
                        {1, 1}, {-1, 1},
                        {1, -1}, {-1, -1}
                };

                Cell targetCell;
                do {
                    int[] chosen = offsets[(int) (Math.random() * offsets.length)];
                    int targetX = monsterCell.getX() + chosen[0];
                    int targetY = monsterCell.getY() + chosen[1];
                    targetCell = map.getCell(targetX, targetY);
                } while (
                        targetCell == null ||
                                targetCell.getActor() != null ||
                                targetCell == monsterCell ||
                                !targetCell.getType().getTileName().equals("floor")
                );

                Player player = map.getPlayer();
                playerCell.setActor(null);
                player.setCell(targetCell);
                targetCell.setActor(player);
            }
        }
    }
}
