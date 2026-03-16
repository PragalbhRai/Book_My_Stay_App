import java.util.*;

/**
 * ================================================================
 * CLASS - Reservation
 * ================================================================
 * Represents a booking request.
 * @version 11.0
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
 * Shared inventory accessed by multiple threads.
 * Synchronization prevents race conditions.
 * @version 11.1
 */

class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Integer> roomCounters = new HashMap<>();

    public RoomInventory() {

        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);

        roomCounters.put("Single", 1);
        roomCounters.put("Double", 1);
        roomCounters.put("Suite", 1);
    }

    public synchronized String allocateRoom(String roomType) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available <= 0) {
            return null;
        }

        int roomNumber = roomCounters.get(roomType);

        String roomId = roomType + "-" + roomNumber;

        roomCounters.put(roomType, roomNumber + 1);

        inventory.put(roomType, available - 1);

        return roomId;
    }

    public void displayInventory() {

        System.out.println("\nRemaining Inventory:");

        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}


/**
 * ================================================================
 * CLASS - BookingProcessor
 * ================================================================
 * Thread responsible for processing booking requests.
 * @version 11.1
 */

class BookingProcessor extends Thread {

    private Queue<Reservation> bookingQueue;
    private RoomInventory inventory;

    public BookingProcessor(Queue<Reservation> bookingQueue, RoomInventory inventory) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
    }

    public void run() {

        while (true) {

            Reservation reservation;

            synchronized (bookingQueue) {

                if (bookingQueue.isEmpty()) {
                    break;
                }

                reservation = bookingQueue.poll();
            }

            String roomId = inventory.allocateRoom(reservation.getRoomType());

            if (roomId != null) {

                System.out.println(
                        "Booking confirmed for Guest: "
                                + reservation.getGuestName()
                                + ", Room ID: "
                                + roomId
                );

            } else {

                System.out.println(
                        "Booking failed for Guest: "
                                + reservation.getGuestName()
                );
            }
        }
    }
}


/**
 * ================================================================
 * MAIN CLASS
 * ================================================================
 * Simulates concurrent booking.
 * @version 11.1
 */

public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation\n");

        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Abhi", "Single"));
        bookingQueue.add(new Reservation("Vanmathi", "Double"));
        bookingQueue.add(new Reservation("Kural", "Suite"));
        bookingQueue.add(new Reservation("Subha", "Single"));

        RoomInventory inventory = new RoomInventory();

        Thread t1 = new BookingProcessor(bookingQueue, inventory);
        Thread t2 = new BookingProcessor(bookingQueue, inventory);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inventory.displayInventory();
    }
}