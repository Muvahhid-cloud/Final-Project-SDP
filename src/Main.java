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
