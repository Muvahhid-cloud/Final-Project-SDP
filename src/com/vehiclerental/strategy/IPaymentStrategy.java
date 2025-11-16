package com.vehiclerental.strategy;
import java.util.Scanner;

public interface IPaymentStrategy {
    boolean pay(double amount, Scanner scanner);
}