package com.vehiclerental.strategy;

public class DailyPricing implements PricingStrategy {
    @Override
    public double calculate(double basePrice, int days) {
        return basePrice * 5 * days;
    }
}