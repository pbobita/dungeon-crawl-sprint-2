package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.dao.ItemDao;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;

public class ChainMail extends Item {
    public ChainMail(Cell cell, char symbol) {
        super(cell, symbol);
    }

    @Override
    public String getTileName() {
        return "chainMail";
    }

    @Override
    public void onPickUp(Player player, Cell cell) {
        Integer boost = ItemDao.getIntStat("chainMail", "maxHealthIncrease");
        int max = player.getMaxHealth();
        player.setMaxHealth(max + boost);
        player.setInventory(player.getInventory() + " ChainMail");
        setEquipped(true);
        cell.setItem(null);
    }
}
