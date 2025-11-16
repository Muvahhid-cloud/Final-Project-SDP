package vehiclerental.abstractfactory;

import vehiclerental.factory.*;

public class ElectricVehicleFactory implements VehicleAbstractFactory {
    @Override
    public Vehicle createCar1() {
        return new BYD();
    }
    @Override
    public Vehicle createCar2() {
        return new Chery();
    }
    @Override
    public Vehicle createCar3() {
        return new Zeekr();
    }
    @Override
    public Vehicle createCar4() {
        return new Lixiang();
    }
}