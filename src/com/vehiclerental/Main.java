package com.vehiclerental;

import com.vehiclerental.abstractfactory.*;
import com.vehiclerental.data.VehicleInventory;
import com.vehiclerental.facade.RentalServiceFacade;
import com.vehiclerental.factory.Vehicle;
import com.vehiclerental.observer.Customer;
import com.vehiclerental.observer.VehicleAvailabilityNotifier;
import com.vehiclerental.strategy.*;
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
                    break;
            }
        }
    }
}