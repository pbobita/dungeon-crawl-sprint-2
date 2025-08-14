package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Cultist extends Monster {
    public Cultist(Cell cell) {
        super(cell, 6, 1, 6);
    }

    @Override
    public String getTileName() {
        return "cultist";
    }

    @Override
    public char getSymbol() {
        return 'c';
    }
}
