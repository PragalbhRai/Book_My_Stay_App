import java.io.*;
import java.util.*;

/**
 * ================================================================
 * CLASS - RoomInventory
 * ================================================================
 * Represents inventory that can be serialized.
 * @version 12.0
 */

class RoomInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Integer> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void displayInventory() {

        System.out.println("\nCurrent Inventory:");

        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}


/**
 * ================================================================
 * CLASS - PersistenceService
 * ================================================================
 * Handles saving and loading system state.
 * @version 12.1
 */

class PersistenceService {

    private static final String FILE_NAME = "inventory.dat";

    public void saveInventory(RoomInventory inventory) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            out.writeObject(inventory);

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {

            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    public RoomInventory loadInventory() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (RoomInventory) in.readObject();

        } catch (Exception e) {

            System.out.println("No valid inventory data found. Starting fresh.");

            return new RoomInventory();
        }
    }
}


/**
 * ================================================================
 * MAIN CLASS
 * ================================================================
 * Demonstrates system recovery using persistence.
 * @version 12.1
 */

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("System Recovery");

        PersistenceService persistence = new PersistenceService();

        RoomInventory inventory = persistence.loadInventory();

        inventory.displayInventory();

        persistence.saveInventory(inventory);
    }
}