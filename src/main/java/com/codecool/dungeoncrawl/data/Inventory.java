package com.codecool.dungeoncrawl.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.codecool.dungeoncrawl.data.items.*;

public class Inventory {
    private final List<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    public boolean contains(String itemName) {
        return items.stream()
                .anyMatch(i -> i.getTileName().equalsIgnoreCase(itemName));
    }

    public List<Item> getItems() {
        return new ArrayList<>(items); // védett másolat
    }

    public String toSaveString() {
        return items.stream()
                .map(Item::getTileName)
                .collect(Collectors.joining(","));
    }

    public void fromSaveString(String data, ItemFactory factory) {
        if (data == null || data.isBlank()) return;

        String[] itemNames = data.split(",");
        for (String name : itemNames) {
            factory.createItemByName(name.trim()).ifPresent(this::add);
        }
    }
}
