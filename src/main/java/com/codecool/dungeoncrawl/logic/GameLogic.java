package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.dao.ItemDao;
import com.codecool.dungeoncrawl.dao.PlayerDao;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.*;
import com.codecool.dungeoncrawl.data.items.NextFloor;
import com.codecool.dungeoncrawl.ui.UI;
import com.codecool.dungeoncrawl.ui.elements.MainStage;
import com.codecool.dungeoncrawl.logic.actors.ItemService;
import com.codecool.dungeoncrawl.logic.actors.MovementService;
import javafx.scene.control.Button;

import java.util.List;
import java.util.Optional;
import com.codecool.dungeoncrawl.ui.elements.StatusPane;
import com.codecool.dungeoncrawl.ui.keyeventhandler.*;

import java.util.Set;

public class GameLogic {
    private GameMap map;
    private String mapData;
    private final PlayerDao playerDAO;
    private final MovementService movementService;
    private final ItemService itemService;
    private final ItemDao itemDAO;
    private MainStage mainStage;
    private UI ui;

    public GameLogic(PlayerDao playerDAO, MovementService movementService, ItemService itemService, ItemDao itemDAO, String mapData, UI ui) {
        this.movementService = movementService;
        this.itemService = itemService;
        this.itemDAO = itemDAO;
        this.mapData = mapData;
        this.map = MapLoader.loadMap(mapData,false);
        this.playerDAO = playerDAO;
        this.ui = ui;
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

    public void setMapData(String mapData) {
        this.mapData = mapData;
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

    public String getPlayerMana() {
        return String.valueOf(map.getPlayer().getCurrentMana());
    }

    public String getPlayerMaxMana() {
        return String.valueOf(map.getPlayer().getMaxMana());
    }

    public GameMap getMap() {
        return map;
    }

    public void handleCombat(Actor attacker, Actor defender) {
        defender.gainDamage(attacker.getAttackPower());
        if (defender.isDead()) {
            handleVictory();
            defender.getCell().setActor(null);
            handleGameEnding();

            return;
        }

        if (defender instanceof Monster && defender.getAbility() != null) {
            defender.getAbility().use(defender);
        }

        attacker.gainDamage(defender.getAttackPower());

        if (attacker.isDead()) {
            handleVictory();
            attacker.getCell().setActor(null);
            handleGameEnding();

            return;
        }

        if (attacker instanceof Monster && attacker.getAbility() != null) {
            attacker.getAbility().use(attacker);
        }
    }

    public void handleGameEnding() {
        if(map.getPlayer().isDead()) {
            mainStage.handleGameOverScreen();
        }
    }

    public void handleVictory() {
        for (Monster monster : map.getMonsters()) {
            if(monster instanceof Boss boss && boss.isDead()) {
                mainStage.handleVictoryScreen();
            }
        }
    }


    public void reloadPlayer() {
        playerDAO.loadLatestPlayerByName(map.getPlayer().getName()).ifPresent(loadedMap -> {
            this.map = loadedMap;

            mainStage.setPlayer(map.getPlayer());

            mainStage.getUi().refresh();
        });
    }

    public void loadPlayerOnNextMap() {
        Player player = map.getPlayer();

        String mapText = MapLoader.loadMapFile("map2.txt");
        GameMap newMap = MapLoader.loadMap(mapText, false);

        Cell newStartingCell = newMap.getCell(3, 11);

        newStartingCell.setActor(player);
        player.setCell(newStartingCell);
        newMap.setPlayer(player);

        this.map = newMap;

        mainStage.setPlayer(player);
        mainStage.getUi().refresh();

        StatusPane statusPane = mainStage.getUi().getMainStage().getStatusPane();
        Set<KeyHandler> keyHandlers = Set.of(
                new Up(statusPane), new Down(statusPane),
                new Left(statusPane), new Right(statusPane),
                new ESC(), new Unstuck(), new Space()
        );
        mainStage.getUi().setKeyHandlers(keyHandlers);
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
            if (player.getCell().getItem() instanceof NextFloor) {
                loadPlayerOnNextMap();
                return;
            }
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

    public List<String> getSavedPlayerNames() {
        return playerDAO.getAllSavedPlayerNames();
    }

    public void loadPlayerByName(String name) {
        Optional<GameMap> optionalMap = playerDAO.loadLatestPlayerByName(name);

        if (optionalMap.isPresent()) {
            GameMap loadedMap = optionalMap.get();
            this.map = loadedMap;

            Player player = loadedMap.getPlayer();
            player.setName(name);
        }
    }


}
