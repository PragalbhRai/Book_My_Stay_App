import java.util.*;

/**
 * ================================================================
 * CLASS - Reservation
 * ================================================================
 *
 * Represents a confirmed reservation.
 *
 * @version 8.0
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
 * CLASS - BookingHistory
 * ================================================================
 *
 * Stores confirmed reservations in insertion order.
 *
 * @version 8.1
 */

class BookingHistory {

    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    public List<Reservation> getHistory() {
        return history;
    }
}


/**
 * ================================================================
 * CLASS - BookingReportService
 * ================================================================
 *
 * Generates booking reports from stored history.
 *
 * @version 8.1
 */

class BookingReportService {

    public void generateReport(List<Reservation> reservations) {

        System.out.println("\nBooking History Report\n");

        for (Reservation r : reservations) {
            System.out.println("Guest: " + r.getGuestName() +
                    ", Room Type: " + r.getRoomType());
        }
    }
}


/**
 * ================================================================
 * MAIN CLASS
 * ================================================================
 *
 * Demonstrates booking history and reporting.
 *
 * @version 8.1
 */

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("Booking History and Reporting");

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        BookingReportService reportService = new BookingReportService();

        reportService.generateReport(history.getHistory());
    }
}