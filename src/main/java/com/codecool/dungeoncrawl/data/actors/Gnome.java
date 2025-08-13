package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Gnome extends Monster { ;

    public Gnome(Cell cell) {
        super(cell, 2, 2, 2);
    }

    @Override
    public char getSymbol() {
        return 'g';
    }

    @Override
    public String getTileName() {
        return "gnome";
    }

}
