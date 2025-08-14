package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Skeleton extends Monster {
    public Skeleton(Cell cell) {
        super(cell, 10, 2, 10);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public char getSymbol() {
        return 's';
    }
}
