package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.dao.ItemDao;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Potion extends Item {

    public Potion(Cell cell, char symbol) {
        super(cell, symbol);
    }

    @Override
    public String getTileName() {
        return "potion";
    }

    @Override
    public void onPickUp(Player player, Cell cell) {
        int current = player.getHealth();
        int max = player.getMaxHealth();
        Integer healValue = ItemDao.getIntStat("sword", "attackPowerIncrease");

        player.setHealth(Math.min(current + healValue, max));
        cell.setItem(null);
    }

    @Override
    public CellType getCellType() {
        return CellType.POTION;
    }
}
