import java.util.*;

/**
 * ================================================================
 * CLASS - AddOnService
 * ================================================================
 *
 * Represents an optional service that can be attached
 * to an existing reservation.
 *
 * @version 7.0
 */

class AddOnService {

    private String serviceName;
    private double price;

    public AddOnService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }
}


/**
 * ================================================================
 * CLASS - AddOnServiceManager
 * ================================================================
 *
 * Manages mapping between reservations and
 * selected add-on services.
 *
 * @version 7.1
 */

class AddOnServiceManager {

    private Map<String, List<AddOnService>> reservationServices;

    public AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {

        reservationServices
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {

        double total = 0;

        List<AddOnService> services = reservationServices.get(reservationId);

        if (services != null) {

            for (AddOnService service : services) {
                total += service.getPrice();
            }

        }

        return total;
    }

    public void displayServices(String reservationId) {

        List<AddOnService> services = reservationServices.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("Selected Add-On Services:");

        for (AddOnService service : services) {
            System.out.println("- " + service.getServiceName() +
                    " : " + service.getPrice());
        }
    }
}


/**
 * ================================================================
 * MAIN CLASS
 * ================================================================
 *
 * Demonstrates add-on service selection.
 *
 * @version 7.1
 */

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection");

        String reservationId = "RES-101";

        AddOnServiceManager manager = new AddOnServiceManager();

        manager.addService(reservationId,
                new AddOnService("Breakfast", 500));

        manager.addService(reservationId,
                new AddOnService("Airport Pickup", 1200));

        manager.addService(reservationId,
                new AddOnService("Spa Access", 2000));

        manager.displayServices(reservationId);

        double totalCost = manager.calculateTotalServiceCost(reservationId);

        System.out.println("\nTotal Add-On Cost: " + totalCost);
    }
}