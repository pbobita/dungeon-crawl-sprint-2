package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;

public class Cat extends NPC{
    boolean isFollowing = false;

    public Cat(Cell cell) {
        super(cell, 9, 1, 9);
    }

    @Override
    public void moveOne(Player player) {
        if(!isFollowing()) {
            return;
        }
        Cell nextCell = checkFollowedPosition(player);
        if (nextCell != getCell()) {
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }
    }

    public Cell checkFollowedPosition(Player player) {
        Cell playerCell = player.getCell();

        if (Math.abs(playerCell.getX() - getCell().getX()) <= 1 &&
                Math.abs(playerCell.getY() - getCell().getY()) <= 1) {
            return getCell();
        }

        int dx = Integer.compare(playerCell.getX(), getCell().getX());
        int dy = Integer.compare(playerCell.getY(), getCell().getY());

        Cell nextCell = getCell().getNeighbor(dx, dy);
        if (isWalkable(nextCell)) {
            return nextCell;
        }

        if (dx != 0) {
            nextCell = getCell().getNeighbor(dx, 0);
            if (isWalkable(nextCell)) {
                return nextCell;
            }
        }

        if (dy != 0) {
            nextCell = getCell().getNeighbor(0, dy);
            if (isWalkable(nextCell)) {
                return nextCell;
            }
        }

        return getCell();
    }

    private boolean isWalkable(Cell cell) {
        return cell != null &&
                cell.getActor() == null &&
                cell.getType() != CellType.WALL
                && cell.getType() != CellType.EMPTY;
    }


    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean isFollowing) {
        this.isFollowing = isFollowing;
    }

    @Override
    public char getSymbol() {
        return '^';
    }

    @Override
    public String getTileName() {
        return "cat";
    }
}