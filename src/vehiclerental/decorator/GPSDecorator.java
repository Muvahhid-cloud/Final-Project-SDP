package vehiclerental.decorator;

import vehiclerental.factory.Vehicle;

public class GPSDecorator extends VehicleDecorator {

    public GPSDecorator(Vehicle v) {
        super(v);
    }

    @Override
    public double getBasePrice() {
        return wrapped.getBasePrice() + 15;
    }

    // We no longer override getType() as it's not needed
}