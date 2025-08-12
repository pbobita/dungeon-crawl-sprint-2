package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Skeleton extends Monster {
    public static final char symbol = 's';
    public Skeleton(Cell cell) {
        super(cell, 10, 2, 10);
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
