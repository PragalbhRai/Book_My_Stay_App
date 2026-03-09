import java.util.HashMap;
import java.util.Map;

/**
 * ================================================================
 * CLASS - RoomInventory
 * ================================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * @version 3.1
 */

class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor initializes the inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Display inventory
    public void displayInventory() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Available Rooms: " + entry.getValue());
        }
    }
}


/**
 * ================================================================
 * ABSTRACT CLASS - Room
 * ================================================================
 */

abstract class Room {

    protected String type;
    protected int beds;
    protected int size;
    protected double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayRoomDetails(int availableRooms) {

        System.out.println(type + ":");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println();
    }
}


/**
 * ================================================================
 * ROOM TYPES
 * ================================================================
 */

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 250, 1500.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 400, 2500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 750, 5000.0);
    }
}


/**
 * ================================================================
 * MAIN CLASS
 * ================================================================
 */

public class BookMyStay {

    public static void main(String[] args) {

        System.out.println("Hotel Room Inventory Status\n");

        RoomInventory inventory = new RoomInventory();

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        single.displayRoomDetails(inventory.getAvailability("Single Room"));
        doubleRoom.displayRoomDetails(inventory.getAvailability("Double Room"));
        suite.displayRoomDetails(inventory.getAvailability("Suite Room"));
    }
}