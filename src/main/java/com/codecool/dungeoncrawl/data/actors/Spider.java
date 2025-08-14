package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Spider extends Monster {
    public Spider(Cell cell) {
        super(cell, 15, 5, 15, null);
    }

    @Override
    public String getTileName() {
        return "spider";
    }

    @Override
    public char getSymbol() {
        return 'p';
    }
}
