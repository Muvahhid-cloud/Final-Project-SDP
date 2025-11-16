package com.vehiclerental.facade;

import com.vehiclerental.data.VehicleInventory;
import com.vehiclerental.decorator.GPSDecorator;
import com.vehiclerental.decorator.InsuranceDecorator;
import com.vehiclerental.factory.Vehicle;
import com.vehiclerental.observer.Observer;
import com.vehiclerental.observer.VehicleAvailabilityNotifier;
import com.vehiclerental.strategy.IPaymentStrategy;
import com.vehiclerental.strategy.PricingStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RentalServiceFacade {

    private VehicleInventory inventory;
    private VehicleAvailabilityNotifier notifier;

    public RentalServiceFacade(VehicleInventory inv, VehicleAvailabilityNotifier not) {
        this.inventory = inv;
        this.notifier = not;
    }

    public void listVehicles() {
        List<Vehicle> petrolCars = new ArrayList<>();
        List<Vehicle> electricCars = new ArrayList<>();

        for (Vehicle v : inventory.getVehicles()) {
            if ("Petrol".equals(v.getFuelType())) {
                petrolCars.add(v);
            } else if ("Electric".equals(v.getFuelType())) {
                electricCars.add(v);
            }
        }

        System.out.println(String.format("%-15s | %-10s | %-15s | %-10s |",
                "Petrol Cars", "Status", "Electric Cars", "Status"));
        System.out.println("----------------|------------|-----------------|------------|");

        int maxRows = Math.max(petrolCars.size(), electricCars.size());

        for (int i = 0; i < maxRows; i++) {
            String pCar = "";
            String pStatus = "";
            if (i < petrolCars.size()) {
                Vehicle v = petrolCars.get(i);
                pCar = v.getName();
                pStatus = v.isRented() ? "Rented" : "";
            }

            String eCar = "";
            String eStatus = "";
            if (i < electricCars.size()) {
                Vehicle v = electricCars.get(i);
                eCar = v.getName();
                eStatus = v.isRented() ? "Rented" : "";
            }

            System.out.println(String.format("%-15s | %-10s | %-15s | %-10s |",
                    pCar, pStatus, eCar, eStatus));
        }
    }

    public void rentVehicle(String name, String renterName,
                            PricingStrategy pricingStrategy,
                            IPaymentStrategy paymentStrategy,
                            boolean gps, boolean ins,
                            Scanner scanner,
                            int duration) {

        Vehicle v = inventory.findByName(name);

        if (v == null) {
            System.out.println(" Vehicle not found.");
            return;
        }

        if (v.isRented()) {
            System.out.println(" Vehicle already rented by " + v.getRentedBy());
            return;
        }

        Vehicle decorated = v;
        if (gps) decorated = new GPSDecorator(decorated);
        if (ins) decorated = new InsuranceDecorator(decorated);

        double cost = pricingStrategy.calculate(decorated.getBasePrice(), duration);

        boolean paymentSuccess = paymentStrategy.pay(cost, scanner);

        if (paymentSuccess) {
            v.setRented(true);
            v.setRentedBy(renterName);

            System.out.println("Vehicle rented successfully!");
            System.out.println("Rented by: " + renterName);
            System.out.println("Total cost: " + cost + " ₸");
        } else {
            System.out.println(" Payment failed. Rental cancelled.");
        }
    }

    public void returnVehicle(String name, String renterName) {
        Vehicle v = inventory.findByName(name);

        if (v == null) {
            System.out.println(" Vehicle not found.");
            return;
        }

        if (!v.isRented()) {
            System.out.println(" This vehicle is not rented.");
            return;
        }

        if (!v.getRentedBy().equals(renterName)) {
            System.out.println(" You cannot return this vehicle. It was rented by: "
                    + v.getRentedBy());
            return;
        }

        v.setRented(false);
        v.setRentedBy(null);

        System.out.println(" Vehicle returned!");

        notifier.notifySubscribers(name + " is now available.");
    }

    public void subscribe(Observer o) {
        notifier.subscribe(o);
        System.out.println("Subscribed!");
    }
}