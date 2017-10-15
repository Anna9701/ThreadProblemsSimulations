package com.annawyrwal.deadlock;

import java.util.ArrayList;

public class DeadlockSimulator {
    private final int TIME_OUT;

    public DeadlockSimulator (final int timeout) {
        TIME_OUT = timeout;

        ResourceHolder firstResource = new ResourceHolder(1, 500.5);
        ResourceHolder secondResource = new ResourceHolder(2, 765.21);

        ArrayList<Thread> threads = new ArrayList<>();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                ResourceHolder.transfer(firstResource, secondResource, 100.50);
            }

            @Override
            public void interrupt() {
                super.interrupt();
                System.err.println("Thread interrupted");
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                ResourceHolder.transfer(secondResource, firstResource, 78.50);
            }

            @Override
            public void interrupt() {
                super.interrupt();
                System.err.println("Thread interrupted");
            }
        };

        threads.add(thread1);
        threads.add(thread2);

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
