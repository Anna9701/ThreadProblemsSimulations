package com.annawyrwal.livelock;

public class Transaction implements Runnable {
    private ResourceHolder source, destination;
    private double amount;

    public Transaction (ResourceHolder from, ResourceHolder to, double amount) {
        source = from;
        destination = to;
        this.amount = amount;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " stared");
        while (!source.tryTransferResources(destination, amount))
            continue;
        System.out.println("Thread " + Thread.currentThread().getName() + " finished.");
    }
}
