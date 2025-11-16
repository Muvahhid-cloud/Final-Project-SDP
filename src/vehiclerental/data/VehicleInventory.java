package vehiclerental.data;

import vehiclerental.factory.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class VehicleInventory {

    private List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public Vehicle findByName(String name) {
        if (name == null) return null;
        String target = name.trim();
        for (Vehicle v : vehicles) {
            if (v.getName().trim().equalsIgnoreCase(target)) {
                return v;
            }
        }
        return null;
    }
}
