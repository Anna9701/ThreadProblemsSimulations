package com.annawyrwal.starvation;

public class ResourcesMonitor implements Runnable {
    private ResourceHolder resourceHolder;

    public ResourcesMonitor (ResourceHolder holder) {
        resourceHolder = holder;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " started");
        while (true) {
            if (resourceHolder.getResource() <= 0) {
                // do sth
                break;
            }
        }
        System.out.println("Resources below 0. Finishing " + Thread.currentThread().getName());
    }
}
