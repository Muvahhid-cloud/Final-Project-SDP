package com.vehiclerental.strategy;

public interface PricingStrategy {
    double calculate(double basePrice, int duration);
}