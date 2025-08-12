package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.items.ChainMail;
import com.codecool.dungeoncrawl.data.items.Key;
import com.codecool.dungeoncrawl.data.items.Potion;
import com.codecool.dungeoncrawl.data.items.Sword;
import com.codecool.dungeoncrawl.data.actors.*;
import com.codecool.dungeoncrawl.ui.elements.StatusPane;

import java.io.InputStream;
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
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'l':
                            cell.setType(CellType.LOOT);
                            break;
                        case 'd':
                            cell.setType(CellType.DOOR);
                            break;
                        case 'n':
                            cell.setType(CellType.NEXTFLOOR);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell, 'k');
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell, 'w');
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new ChainMail(cell, 'a');
                            break;
                        case 't':
                            cell.setType(CellType.FLOOR);
                            new Potion(cell, 't');
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            new Gnome(cell);
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            new Spider(cell);
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            new Cultist(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            if (!skipPlayerSpawn) {
                                map.setPlayer(new Player(cell));
                            }
                            break;
                        case '%':
                            cell.setType(CellType.SAVE_TILE);
                            break;
                        case '*':
                            cell.setType(CellType.LOAD_TILE);
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
