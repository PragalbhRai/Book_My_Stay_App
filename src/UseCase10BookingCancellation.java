import java.util.*;

/**
 * ================================================================
 * CLASS - Reservation
 * ================================================================
 *
 * Represents a confirmed reservation.
 *
 * @version 10.0
 */

class Reservation {

    private String guestName;
    private String roomType;
    private String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }
}


/**
 * ================================================================
 * CLASS - RoomInventory
 * ================================================================
 *
 * Maintains room availability.
 *
 * @version 10.1
 */

class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 1);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);
    }

    public void incrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) + 1);
    }

    public void displayInventory() {

        System.out.println("\nCurrent Inventory");

        for (String type : inventory.keySet()) {
            System.out.println(type + " Rooms Available: " + inventory.get(type));
        }
    }
}


/**
 * ================================================================
 * CLASS - CancellationService
 * ================================================================
 *
 * Handles cancellation and rollback logic.
 *
 * @version 10.1
 */

class CancellationService {

    private Stack<String> rollbackStack = new Stack<>();
    private Map<String, Reservation> confirmedBookings;

    private RoomInventory inventory;

    public CancellationService(RoomInventory inventory) {
        this.inventory = inventory;
        confirmedBookings = new HashMap<>();
    }

    public void confirmReservation(Reservation reservation) {

        confirmedBookings.put(reservation.getRoomId(), reservation);

        System.out.println("Booking confirmed for Guest: "
                + reservation.getGuestName()
                + ", Room ID: "
                + reservation.getRoomId());
    }

    public void cancelReservation(String roomId) {

        if (!confirmedBookings.containsKey(roomId)) {

            System.out.println("Cancellation failed. Reservation not found.");
            return;
        }

        Reservation reservation = confirmedBookings.remove(roomId);

        rollbackStack.push(roomId);

        inventory.incrementRoom(reservation.getRoomType());

        System.out.println("Booking cancelled for Guest: "
                + reservation.getGuestName()
                + ", Room ID: "
                + roomId);
    }

    public void displayRollbackStack() {

        System.out.println("\nRollback Stack:");

        for (String roomId : rollbackStack) {
            System.out.println(roomId);
        }
    }
}


/**
 * ================================================================
 * MAIN CLASS
 * ================================================================
 *
 * Demonstrates booking cancellation and rollback.
 *
 * @version 10.1
 */

public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation & Inventory Rollback");

        RoomInventory inventory = new RoomInventory();

        CancellationService service = new CancellationService(inventory);

        Reservation r1 = new Reservation("Abhi", "Single", "Single-1");
        Reservation r2 = new Reservation("Subha", "Double", "Double-1");

        service.confirmReservation(r1);
        service.confirmReservation(r2);

        service.cancelReservation("Single-1");

        inventory.displayInventory();

        service.displayRollbackStack();
    }
}