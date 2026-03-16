import java.util.*;

/**
 * ================================================================
 * CLASS - InvalidBookingException
 * ================================================================
 *
 * Custom exception for invalid booking scenarios.
 *
 * @version 9.0
 */

class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}


/**
 * ================================================================
 * CLASS - Reservation
 * ================================================================
 *
 * Represents a booking request.
 *
 * @version 9.0
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
 *
 * Maintains room availability.
 *
 * @version 9.1
 */

class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);
    }

    public void validateAndAllocate(Reservation reservation) throws InvalidBookingException {

        String roomType = reservation.getRoomType();

        // Validate room type
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        int available = inventory.get(roomType);

        // Validate availability
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }

        // Allocate room
        inventory.put(roomType, available - 1);
    }
}


/**
 * ================================================================
 * MAIN CLASS
 * ================================================================
 *
 * Demonstrates validation and error handling.
 *
 * @version 9.1
 */

public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("Error Handling and Validation\n");

        RoomInventory inventory = new RoomInventory();

        List<Reservation> requests = new ArrayList<>();

        requests.add(new Reservation("Abhi", "Single"));
        requests.add(new Reservation("Subha", "Double"));
        requests.add(new Reservation("Kumar", "Penthouse")); // Invalid room

        for (Reservation r : requests) {

            try {

                inventory.validateAndAllocate(r);

                System.out.println("Booking successful for Guest: "
                        + r.getGuestName() + ", Room Type: " + r.getRoomType());

            } catch (InvalidBookingException e) {

                System.out.println("Booking failed for Guest: "
                        + r.getGuestName());

                System.out.println("Reason: " + e.getMessage());
            }
        }
    }
}