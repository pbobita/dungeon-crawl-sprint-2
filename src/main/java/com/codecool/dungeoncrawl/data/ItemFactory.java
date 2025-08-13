package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.items.*;

import java.util.Optional;

public class ItemFactory {
    public Optional<Item> createItemByName(String name) {
        return switch (name.toLowerCase()) {
            case "sword" -> Optional.of(new Sword(null, 's'));
            case "potion" -> Optional.of(new Potion(null, 'p'));
            case "key" -> Optional.of(new Key(null, 'k'));
            case "chainmail" -> Optional.of(new ChainMail(null, 'c'));
            default -> Optional.empty();
        };
    }
}

