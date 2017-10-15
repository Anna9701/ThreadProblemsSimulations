package com.annawyrwal;


import com.annawyrwal.deadlock.DeadlockSimulator;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            usage();
        }

        switch (args[0]) {
            case "starvation":
                new StarvationSimulator();
                break;
            case "deadlock":
                new DeadlockSimulator();
                break;
            case "livelock":
                new  com.annawyrwal.livelock.LivelockSimulator();
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
