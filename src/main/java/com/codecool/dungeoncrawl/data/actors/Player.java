package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.*;
import com.codecool.dungeoncrawl.data.items.Item;

public class Player extends Actor {
    private final Inventory inventory;
    private String name;

    public Player(Cell cell) {
        super(cell, 10, 5, 10);
        this.inventory = new Inventory();
    }

    public String getTileName() {
        return "player";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return name != null && name.equalsIgnoreCase("admin");
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public boolean hasItem(String itemName) {
        return inventory.contains(itemName);
    }

    public String getInventoryAsString() {
        return inventory.toSaveString();
    }

    public void loadInventoryFromString(String data, ItemFactory factory) {
        inventory.fromSaveString(data, factory);
    }

    @Override
    public char getSymbol() {
        return '@';
    }

    @Override
    public void placeOn(Cell cell, GameMap map, boolean skipPlayerSpawn) {
        cell.setType(CellType.FLOOR);
        if (!skipPlayerSpawn) {
            map.setPlayer(this);
        }
    }
}
