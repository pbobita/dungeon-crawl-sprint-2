package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Monster;
import com.codecool.dungeoncrawl.data.actors.Player;

import java.util.Set;

public class MovementService {
    public void movePlayer(Player player, int dx, int dy) {

        Cell nextCell = player.getCell().getNeighbor(dx, dy);
        System.out.println("Actor on target cell: " + nextCell.getActor());
        System.out.println("Is monster: " + (nextCell.getActor() instanceof Monster));
        if (nextCell == null || nextCell.getActor() != null) {
            return;
        }

        if (nextCell.getType() == CellType.WALL && !player.isAdmin()) {
            return;
        }

        player.getCell().setActor(null);
        nextCell.setActor(player);
        player.setCell(nextCell);
        System.out.println("Player stepped on: " + player.getCell().getType());
    }

    public boolean canMoveTo(Player player, String tile, Actor actor) {
        if (actor != null || player.isDead()) {
            return false;
        }

        if (player.isAdmin()) {
            return true;
        }

        return !Set.of("wall", "door", "empty").contains(tile)
                || (tile.equals("door") && player.getInventory().contains("Key"));
    }

}
