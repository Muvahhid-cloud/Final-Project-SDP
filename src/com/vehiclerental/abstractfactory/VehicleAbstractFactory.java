package com.vehiclerental.abstractfactory;

import com.vehiclerental.factory.Vehicle;
public interface VehicleAbstractFactory {
    Vehicle createCar1();
    Vehicle createCar2();
    Vehicle createCar3();
    Vehicle createCar4();
}