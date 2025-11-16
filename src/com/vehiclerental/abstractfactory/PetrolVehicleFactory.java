package com.vehiclerental.abstractfactory;

import com.vehiclerental.factory.*;

public class PetrolVehicleFactory implements VehicleAbstractFactory {
    @Override
    public Vehicle createCar1() {
        return new Sedan();
    }
    @Override
    public Vehicle createCar2() {
        return new Cobalt();
    }
    @Override
    public Vehicle createCar3() {
        return new Lexux();
    }
    @Override
    public Vehicle createCar4() {
        return new LandCruizer();
    }
}