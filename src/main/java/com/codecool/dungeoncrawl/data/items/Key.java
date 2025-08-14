package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Key extends Item {

    public Key(Cell cell, char symbol) {
        super(cell, symbol);
    }

    @Override
    public String getTileName() {
        return "key";
    }

    @Override
    public void onPickUp(Player player, Cell cell) {
        player.getInventory().add(this);
        cell.setItem(null);
    }

    @Override
    public CellType getCellType() {
        return CellType.KEY;
    }
}
