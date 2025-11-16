package vehiclerental.decorator;

import vehiclerental.factory.Vehicle;

public abstract class VehicleDecorator extends Vehicle {

    protected Vehicle wrapped;

    public VehicleDecorator(Vehicle v) {
        super(v.getName(), v.getBasePrice(), v.getFuelType());
        this.wrapped = v;
    }

    @Override
    public double getBasePrice() {
        return wrapped.getBasePrice();
    }
}