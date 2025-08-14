package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.MapLoadable;

public abstract class Actor implements Drawable, MapLoadable {
    private Cell cell;
    private String name;
    private int health;
    private int maxHealth;
    private int attackPower;

    public Actor(Cell cell, int health, int attackPower, int maxHealth) {
        this.cell = cell;
        this.health = health;
        this.maxHealth = maxHealth;
        this.attackPower = attackPower;
        this.cell.setActor(this);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public int getAttackPower(){
        return attackPower;
    }

    public void gainDamage(int damage){
        health = Math.max(0, health - damage);
    }

    public boolean isDead(){
        return health == 0;
    }

    public void boostAttackPower(int boost){
        attackPower += boost;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
