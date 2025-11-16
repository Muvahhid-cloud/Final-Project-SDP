package vehiclerental.decorator;

import vehiclerental.factory.Vehicle;

public class InsuranceDecorator extends VehicleDecorator {

    public InsuranceDecorator(Vehicle v) {
        super(v);
    }

    @Override
    public double getBasePrice() {
        return wrapped.getBasePrice() + 30;
    }
}