package com.codecool.dungeoncrawl.data.skills;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Monster;

public class Teleport extends SpecialAbility {

    public Teleport() {
        super("Teleport", 5, 0, 30);
    }

    @Override
    public void use(Actor user) {
        if(user instanceof Monster) {
            Cell monsterCell = user.getCell();

            if(user.getHealth() <= 0.5 * user.getMaxHealth()) {
                GameMap map = monsterCell.getGameMap();
                int x = map.getWidth();
                int y = map.getHeight();

                Cell targetCell;
                do {
                    int randX = (int) (Math.random() * x);
                    int randY = (int) (Math.random() * y);
                    targetCell = map.getCell(randX, randY);
                } while (
                        targetCell == null ||
                                targetCell.getActor() != null ||
                                targetCell == monsterCell ||
                                !targetCell.getType().getTileName().equals("floor")

                );

                monsterCell.setActor(null);
                user.setCell(targetCell);
                targetCell.setActor(user);
            }
        }
    }
}
