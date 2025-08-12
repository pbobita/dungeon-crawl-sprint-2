package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;

public class Crab extends Monster {
    public static final char symbol = 'b';
    private int dirX = 1;

    public Crab(Cell cell) {
        super(cell, 4, 4, 4);
    }

    public void moveOne() {
        if (!tryMove(dirX, 0)) {
            dirX = -dirX;
            tryMove(dirX, 0);
        }
    }

    private boolean tryMove(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if (nextCell == null || nextCell.getActor() != null || nextCell.getType() == CellType.WALL) {
            return false;
        }
        move(dx, dy);
        return true;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public String getTileName() {
        return "crab";
    }

}
