package vehiclerental.observer;
import java.util.ArrayList;
import java.util.List;

public class VehicleAvailabilityNotifier {
    private List<Observer> subs = new ArrayList<>();
    public void subscribe(Observer o) {
        subs.add(o);
    }
    public void notifySubscribers(String msg) {
        for (Observer o : subs) {
            o.update(msg);
        }
    }
}
