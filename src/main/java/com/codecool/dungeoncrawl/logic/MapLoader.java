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

    public static GameMap loadMap(boolean skipPlayerSpawn) {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

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
                        case EMPTY,
                             WALL,
                             LOOT,
                             DOOR,
                             NEXT_FLOOR,
                             SAVE_TILE,
                             FLOOR,
                             LOAD_TILE:
                            cell.setType(cellType);
                            break;
                        case KEY:
                            new Key(cell, 'k');
                            break;
                        case SWORD:
                            new Sword(cell, 'w');
                            break;
                        case CHAIN_MAIL:
                            new ChainMail(cell, 'a');
                        case POTION:
                            new Potion(cell, 't');
                            break;
                        case SKELETON:
                            new Skeleton(cell);
                            break;
                        case GNOME:
                            new Gnome(cell);
                            break;
                        case SPIDER:
                            new Spider(cell);
                            break;
                        case CULTIST:
                            new Cultist(cell);
                            break;
                        case PLAYER:
                            if (!skipPlayerSpawn) {
                                map.setPlayer(new Player(cell));
                            }
                            break;
                        case CRAB:
                            new Crab(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
