import java.util.*;

/**
 * ================================================================
 * CLASS - Reservation
 * ================================================================
 * Represents a booking request.
 */

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}


/**
 * ================================================================
 * CLASS - RoomInventory
 * ================================================================
 */

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decreaseRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}


/**
 * ================================================================
 * CLASS - RoomAllocationService
 * ================================================================
 */

class RoomAllocationService {

    private RoomInventory inventory;

    // Map room type → allocated room IDs
    private HashMap<String, Set<String>> allocatedRooms;

    // Counter to generate IDs
    private HashMap<String, Integer> counters;

    public RoomAllocationService(RoomInventory inventory) {

        this.inventory = inventory;

        allocatedRooms = new HashMap<>();
        counters = new HashMap<>();

        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());

        counters.put("Single", 1);
        counters.put("Double", 1);
        counters.put("Suite", 1);
    }

    public void allocateRoom(Reservation reservation) {

        String type = reservation.getRoomType();

        if (inventory.getAvailability(type) > 0) {

            int id = counters.get(type);
            String roomID = type + "-" + id;

            counters.put(type, id + 1);

            allocatedRooms.get(type).add(roomID);

            inventory.decreaseRoom(type);

            System.out.println(
                    "Booking confirmed for Guest: "
                            + reservation.getGuestName()
                            + ", Room ID: "
                            + roomID
            );

        } else {

            System.out.println(
                    "No rooms available for "
                            + reservation.getGuestName()
            );
        }
    }
}


/**
 * ================================================================
 * MAIN CLASS
 * ================================================================
 */

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Abhi", "Single"));
        bookingQueue.add(new Reservation("Subha", "Single"));
        bookingQueue.add(new Reservation("Vanmathi", "Suite"));

        RoomInventory inventory = new RoomInventory();

        RoomAllocationService service = new RoomAllocationService(inventory);

        while (!bookingQueue.isEmpty()) {

            Reservation r = bookingQueue.poll();

            service.allocateRoom(r);
        }
    }
}