package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Monster;
import com.codecool.dungeoncrawl.data.actors.NPC;
import com.codecool.dungeoncrawl.data.actors.Player;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Monster> getMonsters() {
        List<Monster> result = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Actor actor = getCell(x, y).getActor();
                if (actor instanceof Monster) {
                    result.add((Monster) actor);
                }
            }
        }
        return result;
    }

    public List<NPC> getNPCs() {
        List<NPC> result = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Actor actor = getCell(x, y).getActor();
                if (actor instanceof NPC) {
                    result.add((NPC) actor);
                }
            }
        }
        return result;
    }

    public void moveFollower() {
        List<NPC> npcs = new ArrayList<>(getNPCs());
        for (NPC npc : npcs) {
            npc.moveOne(player);
        }
    }

    public void moveMonsters() {
        List<Monster> monsters = new ArrayList<>(getMonsters());
        for (Monster monster : monsters) {
            monster.moveOne();
        }
    }
}
