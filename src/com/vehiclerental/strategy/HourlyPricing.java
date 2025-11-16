package com.vehiclerental.strategy;

public class HourlyPricing implements PricingStrategy {
    @Override
    public double calculate(double basePrice, int hours) {
        return basePrice * 0.4 * hours;
    }
}