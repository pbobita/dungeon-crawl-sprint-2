package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class Potion extends Item {
    private final int healValue = 1;
    public Potion(Cell cell) {
        super(cell);
    }

    public int getHealValue() {
        return healValue;
    }

    @Override
    public String getTileName() {
        return "potion";
    }
}