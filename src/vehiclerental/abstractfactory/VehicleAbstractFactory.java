package vehiclerental.abstractfactory;

import vehiclerental.factory.Vehicle;
public interface VehicleAbstractFactory {
    Vehicle createCar1();
    Vehicle createCar2();
    Vehicle createCar3();
    Vehicle createCar4();
}