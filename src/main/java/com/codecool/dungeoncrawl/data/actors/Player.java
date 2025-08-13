package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;

public class Player extends Actor {
    private String inventory = "";
    private String name;
    private boolean admin;

    public Player(Cell cell) {
        super(cell, 10, 5, 10);
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

    public boolean isAdmin(){
        return name.equalsIgnoreCase("admin");
    }

    public String getInventory() {return inventory; }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    @Override
    public char getSymbol() {
        return '@';
    }

    @Override
    public void placeOn(Cell cell, GameMap map, boolean skipPlayerSpawn){
        cell.setType(CellType.FLOOR);
        if (!skipPlayerSpawn) {
            map.setPlayer(new Player(cell));
        }
    }


}
