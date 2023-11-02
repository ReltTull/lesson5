package org.example.seminar;

public class Countdown {
    private static volatile boolean switcher = true;

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    switcher = !switcher;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (switcher) {
                for (int i = 100; i >= 0; i--) {
                    System.out.println(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (switcher) {
                    System.out.println("Countdown completed");
                    break;
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
