package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public abstract class Monster extends Actor {
    public Monster(Cell cell, int health, int attackPower, int maxHealth) {
        super(cell, health, attackPower, maxHealth);
    }

    @Override
    public String getTileName() {
        return "monster";
    }

    public void moveOne() {
    }
}
