package com.annawyrwal.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ResourceHolder {
    private double resources;
    private int id;
    final Lock lock = new ReentrantLock();

    public ResourceHolder(int id, double resources) {
        this.id = id;
        this.resources = resources;
    }

    public void takeResources (double amount) {
        try {
            Thread.sleep(1000); // some actions...
        } catch (InterruptedException ex) {
            System.err.println("Interrupted " + Thread.currentThread().getName());
        }
        resources -= amount;
    }

    public void addResources (double amount) {
        try {
            Thread.sleep(1000); // some actions
        } catch (InterruptedException ex) {
            System.err.println("Interrupted " + Thread.currentThread().getName());
        }
        resources += amount;
    }

    public static void transfer (ResourceHolder source, ResourceHolder destination, double amount) {
        System.out.println(Thread.currentThread().getName() + " Transfer started ");
        source.lock.lock();
        source.takeResources(amount);
        destination.lock.lock();
        destination.addResources(amount);
        destination.lock.unlock();
        source.lock.unlock();

        System.out.println(Thread.currentThread().getName() + " Transfer finished");
    }
}
