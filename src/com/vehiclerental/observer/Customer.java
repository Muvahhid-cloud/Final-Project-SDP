package com.vehiclerental.observer;

public class Customer implements Observer {
    private String name;
    public Customer(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.println("[NOTIFICATION for " + name + "] " + message);
    }
}
