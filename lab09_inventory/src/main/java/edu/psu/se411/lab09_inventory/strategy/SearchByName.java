package edu.psu.se411.lab09_inventory.strategy;

import edu.psu.se411.lab09_inventory.model.Item;

public class SearchByName implements SearchStrategy<Item> {
    private String targetName;

    public SearchByName(String targetName) {
        this.targetName = targetName;
    }

    @Override
    public boolean matches(Item item) {
        return item.getName().equalsIgnoreCase(targetName);
    }
}