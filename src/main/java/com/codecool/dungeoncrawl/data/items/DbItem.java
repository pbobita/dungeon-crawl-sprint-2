package com.codecool.dungeoncrawl.data.items;

public class DbItem {
    public final int id;
    public final String name;
    public final Integer attackPowerIncrease;
    public final Integer healthIncrease;
    public final Integer maxHealthIncrease;
    public final boolean equipped;
    public final String symbol;

    public DbItem(int id, String name, Integer atk, Integer hp, Integer maxHp, boolean equipped, String symbol) {
        this.id = id;
        this.name = name;
        this.attackPowerIncrease = atk;
        this.healthIncrease = hp;
        this.maxHealthIncrease = maxHp;
        this.equipped = equipped;
        this.symbol = symbol;
    }
}