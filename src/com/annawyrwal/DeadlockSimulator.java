package com.annawyrwal;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;


public class DeadlockSimulator {
    final int TIME_OUT = 5000;
    Mutex mutex1 = new Mutex();
    Mutex mutex2 = new Mutex();

    public DeadlockSimulator() {
        new Thread( () -> thread1()).start();
        new Thread( () -> thread2()).start();

    }

    void thread1() {
        Thread thread = new Thread() {
            public void run() {
                try {
                    mutex1.acquire();
                    try {
                        sleep(1000);
                        mutex2.acquire();
                    } finally {
                    }
                } catch (InterruptedException ie) {
                    System.err.println("Interrupted after Time Out");
                }
            }
        };
        thread.start();
        try {
            thread.join(TIME_OUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("Thread 1 end");
    }

    void thread2 () {
        Thread thread = new Thread() {
            public void run() {
                try {
                    mutex2.acquire();
                    try {
                        sleep(1000);
                        mutex1.acquire();
                    } finally {
                    }
                } catch (InterruptedException ie) {
                    System.err.println("Interrupted after Time Out");
                }
            }
        };
        thread.start();
        try {
            thread.join(TIME_OUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("Thread 2 ends");
    }
}
