package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Gem extends Item {
    public Gem(Cell cell, char symbol)
    {super(cell,symbol);}

    @Override
    public CellType getCellType() {
        return CellType.GEM;
    }

    @Override
    public void onPickUp(Player player, Cell cell) {
        player.getInventory().add(this);
        cell.setItem(null);
    }

    @Override
    public String getTileName() {
        return "gem";
    }
}
