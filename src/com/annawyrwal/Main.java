package com.annawyrwal;

public class Main {
    private static final int TIME_OUT = 5000;

    public static void main(String[] args) {
        if (args.length < 1) {
            usage();
        }

        switch (args[0]) {
            case "starvation":
                new com.annawyrwal.starvation.StarvationSimulator(TIME_OUT);
                break;
            case "deadlock":
                new com.annawyrwal.deadlock.DeadlockSimulator(TIME_OUT);
                break;
            case "livelock":
                new  com.annawyrwal.livelock.LivelockSimulator(TIME_OUT);
                break;
            default:
                usage();
        }
    }

    private static void usage () {
        System.err.println("Usage: <starvation | deadlock | livelock>");
        System.exit(-1);
    }
}
