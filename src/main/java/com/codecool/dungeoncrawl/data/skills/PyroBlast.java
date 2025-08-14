package com.codecool.dungeoncrawl.data.skills;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Monster;
import com.codecool.dungeoncrawl.data.actors.Player;

import java.util.ArrayList;
import java.util.List;

public class PyroBlast extends SpecialAbility {

    public PyroBlast() {
        super("Pyro Blast", 5, 5, 3);
    }

    @Override
    public void use(Actor user) {
        if (((Player) user).getCurrentMana() < getManaCost()) {
            return;
        }

        Cell playerCell = user.getCell();
        int radius = getRange();
        List<Monster> targets = new ArrayList<>();

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                Cell neighbor = playerCell.getNeighbor(dx, dy);
                if (neighbor != null && neighbor.getActor() instanceof Monster) {
                    targets.add((Monster) neighbor.getActor());
                }
            }
        }

        targets.forEach(target -> {
            target.gainDamage(getAttackPower());

            if(target.isDead()){
                target.getCell().setActor(null);
            }
        });

        ((Player) user).setCurrentMana(((Player) user).getCurrentMana() - getManaCost());
    }
}