package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public interface MapLoadable {
    char getSymbol();
    boolean matches(char symbol);
    void placeOn(Cell cell, GameMap map, boolean skipPlayerSpawn);

}
