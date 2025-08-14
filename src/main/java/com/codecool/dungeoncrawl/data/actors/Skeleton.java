package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.skills.Knockback;

public class Skeleton extends Monster {
    public Skeleton(Cell cell) {
        super(cell, 11, 2, 11, new Knockback());
    }

    @Override
    public char getSymbol() {
        return 's';
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
