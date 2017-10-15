package com.annawyrwal.livelock;


public class LivelockSimulator {
    private final int TIME_OUT = 5000;

    public LivelockSimulator() {
        ResourceHolder firstHolder = new ResourceHolder(1, 500.0);
        ResourceHolder secondHolder = new ResourceHolder(2, 750.5);

        Thread[] threads = new Thread[] {
                new Thread(new Transaction(firstHolder, secondHolder, 100.55)),
                new Thread(new Transaction(secondHolder, firstHolder, 95.07))
        };
        for (Thread thread : threads)
            thread.start();
        for (Thread thread : threads)
            try {
                thread.join(TIME_OUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        for (Thread thread : threads)
            thread.interrupt();

        System.out.println("Threads killed");
    }
}

