package vehiclerental.strategy;
import java.util.Scanner;

public class CashPayment implements IPaymentStrategy {
    @Override
    public boolean pay(double amount, Scanner scanner) {
        System.out.println("Processing Cash payment of " + amount + " ₸");
        System.out.println(" Payment successful!");
        return true;
    }
}