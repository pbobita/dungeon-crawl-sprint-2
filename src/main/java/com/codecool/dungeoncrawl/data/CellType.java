package com.codecool.dungeoncrawl.data;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    LOOT("loot"),
    DOOR("door"),
    KEY("key"),
    SWORD("sword"),
    POTION("potion"),
    NEXTFLOOR("nextFloor"),
    SAVE_TILE("saveTile"),
    LOAD_TILE("loadTile"),
    MONSTER("monster"),;

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
