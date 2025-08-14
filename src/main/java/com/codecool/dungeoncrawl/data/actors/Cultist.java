package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.skills.Teleport;

public class Cultist extends Monster {
    public Cultist(Cell cell) {
        super(cell, 11, 1, 11, new Teleport());
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
