import java.util.HashMap;

/**
 * ================================================================
 * CLASS - RoomInventory
 * ================================================================
 * Use Case 4: Room Search & Availability Check
 * @version 4.1
 */

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}


/**
 * ================================================================
 * ABSTRACT ROOM CLASS
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

    public void displayRoom(int available) {

        System.out.println(type + ":");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + available);
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

        System.out.println("Room Search\n");

        RoomInventory inventory = new RoomInventory();

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvail = inventory.getAvailability("Single Room");
        int doubleAvail = inventory.getAvailability("Double Room");
        int suiteAvail = inventory.getAvailability("Suite Room");

        if (singleAvail > 0)
            single.displayRoom(singleAvail);

        if (doubleAvail > 0)
            doubleRoom.displayRoom(doubleAvail);

        if (suiteAvail > 0)
            suite.displayRoom(suiteAvail);
    }
}