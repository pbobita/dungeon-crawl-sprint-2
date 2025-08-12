package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class Sword extends Item {
    private final int attackBoost = 3;

    public Sword(Cell cell) {
        super(cell);
    }

    public int getAttackBoost() {
        return attackBoost;
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}