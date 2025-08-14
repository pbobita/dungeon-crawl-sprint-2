package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Boss extends Monster {
    public Boss(Cell cell) {
        super(cell, 20, 6, 20, null);
    }

    @Override
    public String getTileName() {
        return "boss";
    }

    @Override
    public char getSymbol() {
        return 'b';
    }
}
