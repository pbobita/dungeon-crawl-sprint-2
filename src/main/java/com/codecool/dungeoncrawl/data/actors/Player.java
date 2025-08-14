package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.*;
import com.codecool.dungeoncrawl.data.skills.PyroBlast;

public class Player extends Actor {
    private final Inventory inventory;
    private String name;
    private int currentMana;
    private int maxMana;

    public Player(Cell cell, int currentMana, int maxMana) {
        super(cell, 10, 5, 10, new PyroBlast());
        this.inventory = new Inventory();
        this.currentMana = currentMana;
        this.maxMana = maxMana;
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

    public int getMaxMana() {
        return maxMana;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }
}
