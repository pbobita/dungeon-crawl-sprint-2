package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Queen extends NPC{
    public Queen(Cell cell) {
        super(cell, 20, 0, 20);
    }

    @Override
    public void moveOne(Player player) {

    }

    @Override
    public String getTileName() {
        return "queen";
    }

    @Override
    public char getSymbol() {
        return 'q';
    }
}
