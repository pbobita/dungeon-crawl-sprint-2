package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.dao.ItemDao;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;

public abstract class Item implements Drawable {
    private Cell cell;
    private char symbol;
    private boolean equipped;

    public Item(Cell cell, char symbol) {
        this.cell = cell;
        this.symbol = symbol;
        if (cell != null) {
            this.cell.setItem(this);
        }
    }

    public Cell getCell() { return cell; }
    public void setEquipped(boolean equipped) { this.equipped = equipped; }
    public void onPickUp(Player player, Cell cell) {}

    public char getSymbol() {
        return symbol;
    }

    public abstract CellType getCellType();
}
