import java.util.LinkedList;
import java.util.Queue;

/**
 * ================================================================
 * CLASS - Reservation
 * ================================================================
 *
 * Represents a guest's booking request.
 *
 * @version 5.1
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
 * CLASS - BookingRequestQueue
 * ================================================================
 *
 * Stores booking requests using FIFO ordering.
 */

class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    // Process requests in FIFO order
    public void processRequests() {

        System.out.println("Booking Request Queue");

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();

            System.out.println(
                    "Processing booking for Guest: "
                            + r.getGuestName()
                            + ", Room Type: "
                            + r.getRoomType());
        }
    }
}


/**
 * ================================================================
 * MAIN CLASS
 * ================================================================
 */

public class BookMyStay {

    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Guests submit booking requests
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Double"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Process queue
        bookingQueue.processRequests();
    }
}