package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.skills.SpecialAbility;

public abstract class Monster extends Actor {
    private SpecialAbility specialAbility;

    public Monster(Cell cell, int health, int attackPower, int maxHealth, SpecialAbility specialAbility) {
        super(cell, health, attackPower, maxHealth, specialAbility);
    }

    @Override
    public String getTileName() {
        return "monster";
    }

    public void moveOne() {
    }
}
