package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;

public class NextFloor extends Item {
    public NextFloor(Cell cell, char symbol) {
        super(cell, symbol);
    }

    @Override
    public String getTileName() {
        return "nextFloor";
    }

    @Override
    public CellType getCellType() {
        return CellType.NEXT_FLOOR;
    }
}
