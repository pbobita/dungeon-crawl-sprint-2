package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public abstract class NPC extends Actor {

    public NPC(Cell cell, int health, int attackPower, int maxHealth) {
        super(cell, health, attackPower, maxHealth, null);
    }

    public abstract void moveOne(Player player);
    public abstract void interact(Player player);

    @Override
    public String getTileName() {
        return "npc";
    }

}
