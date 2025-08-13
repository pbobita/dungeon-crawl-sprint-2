package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Item;

public class PlayerService {

    public void pickUpItem(Player player, Item item) {
        if (item == null) return;

        String inventory = player.getInventory();
        player.setInventory(inventory + item.getTileName() + ", ");
        //item.getCell().setItem(null);
    }

    public void heal(Player player, int amount) {
        int newHealth = Math.min(player.getMaxHealth(), player.getHealth() + amount);
        player.setHealth(newHealth);
    }

    public void takeDamage(Player player, int damage) {
        player.gainDamage(damage);
        if (player.isDead()) {
            handleDeath(player);
        }
    }

    public void boostAttack(Player player, int boost) {
        player.boostAttackPower(boost);
    }

    private void handleDeath(Player player) {
        System.out.println("Player has died.");
    }
}
