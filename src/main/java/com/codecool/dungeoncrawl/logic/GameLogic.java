package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.dao.ItemDao;
import com.codecool.dungeoncrawl.dao.PlayerDao;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Monster;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Potion;
import com.codecool.dungeoncrawl.data.items.Sword;

import java.util.Set;

public class GameLogic {
    private GameMap map;
    private final PlayerDao playerDAO;
    private final ItemDao itemDAO;

    public GameLogic(PlayerDao playerDAO, ItemDao itemDAO) {
        this.itemDAO = itemDAO;
        this.map = MapLoader.loadMap(false);
        this.playerDAO = playerDAO;
    }

    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {

    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getHealth());
    }

    public String getPlayerMaxHealth() { return Integer.toString(map.getPlayer().getMaxHealth()); }

    public String getPlayerDamage() { return Integer.toString(map.getPlayer().getAttackPower()); }

    public String getPlayerInventory() {return map.getPlayer().getInventory();}



    public GameMap getMap() {
        return map;
    }

    public void handleCombat(Actor attacker, Actor defender) {
        defender.gainDamage(attacker.getAttackPower());
        if (defender.isDead()) {
            defender.getCell().setActor(null);
        } else {
            attacker.gainDamage(defender.getAttackPower());
            if (attacker.isDead()) {
                attacker.getCell().setActor(null);
            }
        }
    }

    public void reloadPlayer() {
        playerDAO.loadPlayer().ifPresent(loadedMap -> this.map = loadedMap);
    }

    private boolean canMoveTo(String tile, Actor actor, Player player) {
        return (actor == null
                    && !player.isDead()
                    && !Set.of("wall", "door", "empty").contains(tile))
                    || (tile.equals("door") && player.getInventory().contains("Key")
        );
    }

    private void interactWithTile(Cell cell) {
        if (cell.getItem() != null) {
            cell.getItem().onPickUp(map.getPlayer(), cell);
        } else if (cell.getTileName().equals("saveTile")) {
            playerDAO.savePlayer(map.getPlayer());
        }
    }

    public void movePlayer(int dx, int dy, boolean isAdmin) {
        Player player = map.getPlayer();
        Cell targetCell = map.getCell(player.getX() + dx, player.getY() + dy);

        if (canMoveTo(targetCell.getTileName(), targetCell.getActor(), player)) {
            player.move(dx, dy);
        } else if (targetCell.getActor() instanceof Monster) {
            handleCombat(player, targetCell.getActor());
        } else if (isAdmin) {
            player.adminMove(dx, dy);
        }

        interactWithTile(player.getCell());
    }



}
