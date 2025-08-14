package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.dao.ItemDao;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
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
        player.setMaxHealth(player.getMaxHealth() + boost);
        player.getInventory().add(this);
        setEquipped(true);
        cell.setItem(null);
    }

    @Override
    public CellType getCellType() {
        return CellType.CHAIN_MAIL;
    }
}
