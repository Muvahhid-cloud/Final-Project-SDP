package vehiclerental;

import vehiclerental.abstractfactory.*;
import vehiclerental.data.VehicleInventory;
import vehiclerental.facade.RentalServiceFacade;
import vehiclerental.factory.Vehicle;
import vehiclerental.observer.Customer;
import vehiclerental.observer.VehicleAvailabilityNotifier;
import vehiclerental.strategy.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        VehicleAbstractFactory petrolFactory = new PetrolVehicleFactory();
        VehicleAbstractFactory electricFactory = new ElectricVehicleFactory();

        VehicleInventory inventory = new VehicleInventory();
        VehicleAvailabilityNotifier notifier = new VehicleAvailabilityNotifier();
        RentalServiceFacade service = new RentalServiceFacade(inventory, notifier);

        inventory.addVehicle(petrolFactory.createCar1()); // Sedan
        inventory.addVehicle(petrolFactory.createCar2()); // Cobalt
        inventory.addVehicle(petrolFactory.createCar3()); // Lexux
        inventory.addVehicle(petrolFactory.createCar4()); // Land Cruizer

        inventory.addVehicle(electricFactory.createCar1()); // BYD
        inventory.addVehicle(electricFactory.createCar2()); // Chery
        inventory.addVehicle(electricFactory.createCar3()); // Zeekr
        inventory.addVehicle(electricFactory.createCar4()); // Lixiang

        while (true) {
            System.out.println("\n--- Vehicle Rental Service ---");
            System.out.println("1. View vehicles");
            System.out.println("2. Rent");
            System.out.println("3. Return");
            System.out.println("4. Subscribe");
            System.out.println("5. Exit");
            System.out.print("Option: ");

            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1:
                    service.listVehicles();
                    break;

                case 2:
                    System.out.print("Your name: ");
                    String renter = sc.nextLine();
                    System.out.print("Enter vehicle name: ");
                    String name = sc.nextLine();
                    System.out.print("Pricing (1 Hourly, 2 Daily): ");
                    int p = sc.nextInt();
                    sc.nextLine();
                    PricingStrategy ps = (p == 1) ? new HourlyPricing() : new DailyPricing();

                    int duration;
                    if (p == 1) {
                        System.out.print("Enter number of hours: ");
                    } else {
                        System.out.print("Enter number of days: ");
                    }
                    duration = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Payment (1 Card, 2 Cash): ");
                    int payOpt = sc.nextInt();
                    sc.nextLine();
                    IPaymentStrategy paymentStrategy = (payOpt == 1)
                            ? new CreditCardPayment()
                            : new CashPayment();

                    System.out.print("GPS? (y/n): ");
                    boolean gps = sc.nextLine().equalsIgnoreCase("y");

                    System.out.print("Insurance? (y/n): ");
                    boolean ins = sc.nextLine().equalsIgnoreCase("y");

                    service.rentVehicle(name, renter, ps, paymentStrategy, gps, ins, sc, duration);
                    break;

                case 3:
                    System.out.print("Your name: ");
                    String rn = sc.nextLine();

                    System.out.print("Vehicle name: ");
                    String vn = sc.nextLine();

                    service.returnVehicle(vn, rn);
                    break;

                case 4:
                    System.out.print("Enter your name: ");
                    String n = sc.nextLine();
                    service.subscribe(new Customer(n));
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid.");
            }
        }
    }
}
