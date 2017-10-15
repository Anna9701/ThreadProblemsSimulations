package com.annawyrwal.livelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ResourceHolder {
    private double resource;
    private int id;
    private Lock lock = new ReentrantLock();

    public ResourceHolder(int id, double resource) {
        this.resource = resource;
        this.id = id;
    }

     public boolean takeResource (double amount) {
        if (this.lock.tryLock()) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                System.err.println("Interrupted " + Thread.currentThread().getName());
                return true;
            }
            resource -= amount;
            return true;
        }
        return false;
    }

    public boolean addResource (double amount) {
        if (this.lock.tryLock()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.err.println("Interrupted " + Thread.currentThread().getName());
                return true;
            }
            resource += amount;
            return true;
        }
        return false;
    }

    public boolean tryTransferResources (ResourceHolder destination, double amount) {
        if (this.takeResource(amount)) {
            if (destination.addResource(amount)) {
                return true;
            } else {
                this.addResource(amount);
            }
        }
        return false;
    }
}
