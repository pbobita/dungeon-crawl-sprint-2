package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell, 10, 5, 10);
    }

    public String getTileName() {
        return "player";
    }
}
