package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Potion;
import com.codecool.dungeoncrawl.data.items.Sword;

public class ItemService {
    public void pickUpKey(Player player, Cell cell) {
        player.setInventory(player.getInventory() + cell.getItem().getTileName());
        cell.setItem(null);
    }

    public int calculatePotionHeal(Player player, Cell cell) {
        int currentHealth = player.getHealth();
        int maxHealth = player.getMaxHealth();
        Potion potion = new Potion(cell);

        int healedHealth = currentHealth + potion.getHealValue();
        return Math.min(healedHealth, maxHealth);
    }

    public void consumePotion(Player player, Cell cell) {
        player.setHealth(calculatePotionHeal(player, cell));
        cell.setItem(null);
    }

    public void pickUpSword(Player player, Cell cell) {
        Sword sword = (Sword) cell.getItem();
        player.boostAttackPower(sword.getAttackBoost());
        cell.setItem(null);
    }
}

