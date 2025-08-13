package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.dao.ItemDao;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Sword extends Item {
    public Sword(Cell cell, char symbol) {
        super(cell, symbol);
    }

    @Override
    public String getTileName() {
        return "sword";
    }

    @Override
    public void onPickUp(Player player, Cell cell) {
        Integer boost = ItemDao.getIntStat("sword", "attackPowerIncrease");
        player.boostAttackPower(boost);
        player.getInventory().add(this);
        setEquipped(true);
        cell.setItem(null);
    }
}
