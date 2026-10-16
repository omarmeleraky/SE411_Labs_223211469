package edu.psu.se411.lab09_inventory;

import edu.psu.se411.lab09_inventory.model.*;
import edu.psu.se411.lab09_inventory.strategy.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);
    
    public static void main(String[] args) {
        logger.info("Application is starting...");

        Inventory<Item> inventory = new Inventory<>();
        inventory.add(new Book("B001", "Concurrency in Java", "Brian Goetz"));
        inventory.add(new Book("B002", "Clean Code", "Robert C. Martin"));
        inventory.add(new ElectronicDevice("E001", "Galaxy S24", "Smartphones"));
        inventory.add(new ElectronicDevice("E002", "ThinkPad T14", "Laptops"));

        displayInventory(inventory);

        logger.info("Searching for 'Concurrency in Java'");
        List<Item> foundByName = inventory.findItems(new SearchByName("Concurrency in Java"));
        for (Item item : foundByName) {
            logger.info("Found: {}", item);
        }

        logger.info("Searching by Author 'Robert C. Martin'");
        List<Item> foundByAuthor = inventory.findItems(new SearchByAuthor("Robert C. Martin"));
        for (Item item : foundByAuthor) {
            logger.info("Found: {}", item);
        }

        logger.info("Searching by Category 'Smartphones'");
        List<Item> foundByCategory = inventory.findItems(new SearchByCategory("Smartphones"));
        for (Item item : foundByCategory) {
            logger.info("Found: {}", item);
        }

        logger.info("Searching using Lambda expression (Id == 'E002')");
        List<Item> foundByLambda = inventory.findItems(item -> item.getId().equals("E002"));
        for (Item item : foundByLambda) {
            logger.info("Found: {}", item);
        }

        logger.info("Application is closing...");
    }

    public static void displayInventory(Inventory<?> inventory) {
        logger.info("Displaying Entire Inventory");
        for (Object item : inventory.getAllItems()) {
            logger.info(item.toString());
        }
    }
}