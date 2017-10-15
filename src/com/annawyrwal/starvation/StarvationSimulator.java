package com.annawyrwal.starvation;

import java.util.regex.Pattern;

public class StarvationSimulator {
    private final int TIME_OUT;

    public StarvationSimulator(final int timeout) {
        TIME_OUT = timeout;

        runTestSimulation();
    }

    private void runTestSimulation() {
        ResourceHolder holder1 = new ResourceHolder(1, 500.50);
        ResourceHolder holder2 = new ResourceHolder(2, 290.2);

        Thread resourceMonitorThread = new Thread(new ResourcesMonitor(holder1), "ResourcesMonitor");
        Thread firstTransactionThread = new Thread(new Transaction(holder1, holder2, 220.21), "FirstTransaction");
        Thread secondTransactionThread = new Thread(new Transaction(holder2, holder1, 10.10), "SecondTransaction");

        resourceMonitorThread.setPriority(Thread.MAX_PRIORITY);
        firstTransactionThread.setPriority(Thread.MIN_PRIORITY);
        secondTransactionThread.setPriority(Thread.MIN_PRIORITY);

        resourceMonitorThread.start();
        try {
            Thread.sleep(1000); // monitor is working... time is passing
        } catch (InterruptedException ex) {
            System.err.println("Interrupted " + ex.getMessage());
        }

        firstTransactionThread.start(); // after some time transactions tries to execute
        secondTransactionThread.start();

        try {
            resourceMonitorThread.join(TIME_OUT);
        } catch (InterruptedException ex) {
            System.err.println("Interrupted " + Thread.currentThread().getName());
        }

        resourceMonitorThread.interrupt();
    }
}
