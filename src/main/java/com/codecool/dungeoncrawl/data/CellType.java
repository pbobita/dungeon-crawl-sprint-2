package com.codecool.dungeoncrawl.data;

import java.util.Arrays;
import java.util.Optional;

public enum CellType {
    EMPTY("empty", ' '),
    FLOOR("floor", '.'),
    WALL("wall", '#'),
    LOOT("loot", 'l'),
    DOOR("door", 'd'),
    KEY("key", 'k'),
    SWORD("sword", 'w'),
    POTION("potion", 't'),
    NEXT_FLOOR("nextFloor", 'n'),
    SAVE_TILE("saveTile", '%'),
    SKELETON("skeleton", 's'),
    GNOME("gnome", 'g'),
    SPIDER("spider", 'p'),
    CULTIST("cultist", 'c'),
    PLAYER("player", '@'),
    LOAD_TILE("loadTile", '*'),
    CHAIN_MAIL("chainMail", 'a'),
    CRAB("crab", 'b'),
    CAT("cat", '^'),
    QUEEN("queen", 'q'),
    GEM("gem", '8');


    private final String tileName;

    private final char symbol;

    CellType(String tileName, char symbol) {
        this.tileName = tileName;
        this.symbol = symbol;
    }

    public String getTileName() {
        return tileName;
    }

    public char getSymbol() {
        return symbol;
    }

    public static Optional<CellType> getBySymbol(char symbol){
        return Arrays.stream(CellType.values()).filter(e -> e.symbol == symbol).findFirst();
    }
}
