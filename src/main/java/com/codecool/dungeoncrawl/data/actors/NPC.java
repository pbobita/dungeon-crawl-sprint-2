package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public abstract class NPC extends Actor {

    public NPC(Cell cell, int health, int attackPower, int maxHealth) {
        super(cell, health, attackPower, maxHealth, null);
    }

    public abstract char getSymbol();

    @Override
    public String getTileName() {
        return "npc";
    }

    public void moveOne() {
    }
}
