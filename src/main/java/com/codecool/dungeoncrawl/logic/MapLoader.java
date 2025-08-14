package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.items.ChainMail;
import com.codecool.dungeoncrawl.data.items.Key;
import com.codecool.dungeoncrawl.data.items.Potion;
import com.codecool.dungeoncrawl.data.items.Sword;
import com.codecool.dungeoncrawl.data.actors.*;

import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

public class MapLoader {
    public static String loadMapFile(String filename) {
        InputStream is = MapLoader.class.getResourceAsStream("/" + filename);
        if (is == null) {
            throw new RuntimeException("Map file not found: " + filename);
        }

        Scanner scanner = new Scanner(is);
        StringBuilder mapData = new StringBuilder();

        while (scanner.hasNextLine()) {
            mapData.append(scanner.nextLine()).append("\n");
        }

        return mapData.toString();
    }

    public static GameMap loadMap(String mapData, boolean playerSpawnByLoad) {
        Scanner scanner = new Scanner(mapData);

        int width = scanner.nextInt();
        int height = scanner.nextInt();
        scanner.nextLine();

        GameMap map = new GameMap(width, height, CellType.EMPTY);

        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    Optional<CellType> optionalSymbol = CellType.getBySymbol(line.charAt(x));
                    if (optionalSymbol.isEmpty()) {
                        throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                    CellType cellType = optionalSymbol.get();
                    cell.setType(CellType.FLOOR);

                    switch (cellType) {
                        case EMPTY, WALL, LOOT, DOOR, NEXT_FLOOR, SAVE_TILE, FLOOR, LOAD_TILE -> cell.setType(cellType);
                        case KEY -> new Key(cell, 'k');
                        case SWORD -> new Sword(cell, 'w');
                        case CHAIN_MAIL -> new ChainMail(cell, 'a');
                        case POTION -> new Potion(cell, 't');
                        case SKELETON -> new Skeleton(cell);
                        case GNOME -> new Gnome(cell);
                        case SPIDER -> new Spider(cell);
                        case CULTIST -> new Cultist(cell);
                        case PLAYER -> {
                            map.setPlayer(new Player(cell));
                            if (playerSpawnByLoad) {
                                cell.setType(CellType.SAVE_TILE);
                            }
                        }
                        case CRAB -> new Crab(cell);
                        default -> throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }

        return map;
    }
}
