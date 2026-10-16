package edu.psu.se411.lab09_inventory.strategy;

import edu.psu.se411.lab09_inventory.model.Book;
import edu.psu.se411.lab09_inventory.model.Item;

public class SearchByAuthor implements SearchStrategy<Item> {
    private String targetAuthor;

    public SearchByAuthor(String targetAuthor) {
        this.targetAuthor = targetAuthor;
    }

    @Override
    public boolean matches(Item item) {
        if (item instanceof Book) {
            Book book = (Book) item;
            return book.getAuthorName().equalsIgnoreCase(targetAuthor);
        }
        return false;
    }
}