package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Cat extends NPC{
    public Cat(Cell cell, int health, int attackPower, int maxHealth) {
        super(cell, health, attackPower, maxHealth);
    }

    @Override
    public char getSymbol() {
        return '^';
    }
}
