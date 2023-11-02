package org.example;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Interface thread - " + Thread.currentThread());
    }
}
