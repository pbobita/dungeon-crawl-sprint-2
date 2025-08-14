package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.dao.ItemDao;
import com.codecool.dungeoncrawl.dao.PlayerDao;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.*;
import com.codecool.dungeoncrawl.ui.elements.MainStage;
import com.codecool.dungeoncrawl.logic.actors.ItemService;
import com.codecool.dungeoncrawl.logic.actors.MovementService;

public class GameLogic {
    private GameMap map;
    private String mapData;
    private final PlayerDao playerDAO;
    private final MovementService movementService;
    private final ItemService itemService;
    private final ItemDao itemDAO;
    private MainStage mainStage;

    public GameLogic(PlayerDao playerDAO, MovementService movementService, ItemService itemService, ItemDao itemDAO, String mapData) {
        this.movementService = movementService;
        this.itemService = itemService;
        this.itemDAO = itemDAO;
        this.mapData = mapData;
        this.map = MapLoader.loadMap(mapData,false);
        this.playerDAO = playerDAO;
    }

    public void setMainStage(MainStage mainStage) {
        this.mainStage = mainStage;
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

    public String getPlayerMaxHealth() {
        return Integer.toString(map.getPlayer().getMaxHealth());
    }

    public String getPlayerDamage() {
        return Integer.toString(map.getPlayer().getAttackPower());
    }

    public String getPlayerInventory() {return map.getPlayer().getInventory().toSaveString();}

    public String getPlayerName() {
        return map.getPlayer().getName();
    }


    public GameMap getMap() {
        return map;
    }

    public void handleCombat(Actor attacker, Actor defender) {
        defender.gainDamage(attacker.getAttackPower());
        if (defender.isDead()) {
            defender.getCell().setActor(null);
            handleGameEnding();
        } else {
            attacker.gainDamage(defender.getAttackPower());
            if (attacker.isDead()) {
                attacker.getCell().setActor(null);
                handleGameEnding();
            }
        }
    }

    public void handleGameEnding() {
        if(map.getPlayer().isDead()) {
            mainStage.handleGameOverScreen();
        }
    }

    public void reloadPlayer() {
        playerDAO.loadLatestPlayerByName(map.getPlayer().getName()).ifPresent(loadedMap -> {
            this.map = loadedMap;

            mainStage.setPlayer(map.getPlayer());

            mainStage.getUi().refresh();
        });
    }


    private void interactWithTile(Cell cell) {
        if (cell.getItem() != null) {
            cell.getItem().onPickUp(map.getPlayer(), cell);
        } else if (cell.getType() == CellType.SAVE_TILE) {
            playerDAO.savePlayer(map);
        }
    }

    public void interactWithNPC(NPC npc, Player player) {
          npc.interact(player);
    }

    public void movePlayer(int dx, int dy) {
        Player player = map.getPlayer();
        Cell targetCell = map.getCell(player.getX() + dx, player.getY() + dy);

        if (movementService.canMoveTo(player, targetCell.getTileName(), targetCell.getActor())) {
            movementService.movePlayer(player, dx, dy);
        } else if (targetCell.getActor() instanceof Monster) {
            handleCombat(player, targetCell.getActor());
        } else if (targetCell.getActor() instanceof NPC) {
            interactWithNPC((NPC) targetCell.getActor(), player);
        }
        interactWithTile(player.getCell());

    }

    public void catMoveOutOfWay(Player player, Cat cat) {
        Cell catCell = cat.getCell();
        Cell playerCell = player.getCell();
        int cx = catCell.getX();
        int cy = catCell.getY();

        Cell[] freeNeighbors = {
                map.getCell(cx + 1, cy),
                map.getCell(cx - 1, cy),
                map.getCell(cx, cy + 1),
                map.getCell(cx, cy - 1)
        };

        for (Cell c : freeNeighbors) {
            if (c != null && c.getActor() == null && c.getType() != CellType.WALL) {
                playerCell.setActor(null);
                c.setActor(player);
                player.setCell(c);
                break;
            }
        }
    }

    public void startNewGame() {
        String mapText = MapLoader.loadMapFile("map.txt");
        this.map = MapLoader.loadMap(mapText, false);
    }

}
