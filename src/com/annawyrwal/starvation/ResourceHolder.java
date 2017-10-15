package com.annawyrwal.starvation;

public class ResourceHolder {
    private double resource;
    private int id;

    public ResourceHolder(int id, double resource) {
        this.id = id;
        this.resource = resource;
    }

    synchronized double getResource () {
        try {
            Thread.sleep(1000); // simulate some actions...
        } catch (InterruptedException ex) {
            System.err.println("Interrupted " + Thread.currentThread().getName());
        }
        return resource;
    }

    synchronized void takeResource (double amount) {
        resource -= amount;
    }

    synchronized void addResource (double amount) {
        resource += amount;
    }

    synchronized void transfer (ResourceHolder destination, double amount) {
        takeResource(amount);
        destination.addResource(amount);
    }
}
