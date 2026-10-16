package edu.psu.se411.lab09_inventory.strategy;

import edu.psu.se411.lab09_inventory.model.ElectronicDevice;
import edu.psu.se411.lab09_inventory.model.Item;

public class SearchByCategory implements SearchStrategy<Item> {
    private String targetCategory;

    public SearchByCategory(String targetCategory) {
        this.targetCategory = targetCategory;
    }

    @Override
    public boolean matches(Item item) {
        if (item instanceof ElectronicDevice) {
            ElectronicDevice device = (ElectronicDevice) item;
            return device.getCategory().equalsIgnoreCase(targetCategory);
        }
        return false;
    }
}