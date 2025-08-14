package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public class MapSerializer {

    public static String serializeMap(GameMap map) {
        StringBuilder builder = new StringBuilder();
        builder.append(map.getWidth()).append(" ").append(map.getHeight()).append("\n");

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Cell cell = map.getCell(x, y);

                if (cell.getActor() != null) {
                    builder.append(cell.getActor().getSymbol());
                } else if (cell.getItem() != null) {
                    builder.append(cell.getItem().getCellType().getSymbol());
                } else {
                    builder.append(cell.getType().getSymbol());
                }
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}

