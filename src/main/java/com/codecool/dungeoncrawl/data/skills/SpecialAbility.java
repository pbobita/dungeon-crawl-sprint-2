package com.codecool.dungeoncrawl.data.skills;

import com.codecool.dungeoncrawl.data.actors.Actor;

public abstract class SpecialAbility {
    private final String name;
    private int attackPower;
    private final int manaCost;
    private final int range;

    public SpecialAbility(String name, int manaCost, int attackPower, int range) {
        this.name = name;
        this.manaCost = manaCost;
        this.attackPower = attackPower;
        this.range = range;
    }

    public abstract void use(Actor user);

    public int getAttackPower() {
        return attackPower;
    }

    public int getRange() {
        return range;
    }

    public int getManaCost() {
        return manaCost;
    }
}
