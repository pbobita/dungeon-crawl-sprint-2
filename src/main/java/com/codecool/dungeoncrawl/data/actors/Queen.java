package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.dao.ItemDao;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;

public class Queen extends NPC{
    public Queen(Cell cell) {
        super(cell, 20, 0, 20);
    }

    public void interact(Player player) {
        if(checkGemStatus(player)) {
            int current = player.getHealth();
            int max = player.getMaxHealth();
            player.setHealth(Math.min(current + 2, max));
            Item gem = player.getInventory().getItem("gem");
            player.getInventory().removeItem(gem);
        }
    }

     boolean checkGemStatus(Player player) {
        return player.getInventory().contains("gem");
    }

    @Override
    public void moveOne(Player player) {

    }

    @Override
    public String getTileName() {
        return "queen";
    }

    @Override
    public char getSymbol() {
        return 'q';
    }
}
