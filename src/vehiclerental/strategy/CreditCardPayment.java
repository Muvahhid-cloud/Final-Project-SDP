package vehiclerental.strategy;
import java.util.Scanner;

public class CreditCardPayment implements IPaymentStrategy {
    @Override
    public boolean pay(double amount, Scanner scanner) {
        System.out.println("Processing Credit Card payment of " + amount + " ₸");
        System.out.print("Enter Card Number (16 digits): ");
        String card = scanner.nextLine();
        System.out.print("Enter CVV (3 digits): ");
        String cvv = scanner.nextLine();
        if (!isValidCard(card, cvv)) {
            System.out.println(" Invalid card details. Payment failed.");
            return false;
        }
        System.out.println(" Payment successful!");
        return true;
    }

    private boolean isValidCard(String cardNumber, String cvv) {
        String cleanedCard = cardNumber.replaceAll("\\s+", "");
        if (cleanedCard.length() != 16 || !isNumeric(cleanedCard)) {
            System.out.println("Error: Card number must be 16 digits.");
            return false;
        }
        if (cvv.length() != 3 || !isNumeric(cvv)) {
            System.out.println("Error: CVV must be 3 digits.");
            return false;
        }
        return true;
    }
    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}