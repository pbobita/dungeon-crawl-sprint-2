package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.*;

public class Player extends Actor {
    private Inventory inventory;
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

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public char getSymbol() {
        return '@';
    }
}
