package org.example;

public class Main {
    public static void main(String[] args) {
//        for (int i = 0; i < 3; i++) {
//            new MyThread().start();
//        }
//        for (int i = 0; i < 3; i++) {
//            new Thread(new MyRunnable()).start();
//        }
//        for (int i = 0; i < 3; i++) {
//            new Thread(() -> {
//                System.out.println("Lambda thread - " + Thread.currentThread());
//            }).start();
//        }

        Thread tic = new Thread(new TicTak("["));
        Thread tac = new Thread(new TicTak("]"));
        tic.setDaemon(true);
        tac.setDaemon(true);
        tic.start();
        tac.start();
    }
}