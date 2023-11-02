package org.example.seminar;

public class ex1 {
    private static final Object objectA = new Object();
    private static final Object objectB = new Object();

    public static void lock1() {
        synchronized (objectA) {
            System.out.println(Thread.currentThread() + " locked on ObjectA");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (objectB) {
                System.out.println(Thread.currentThread() + " locked on ObjectB");
            }
        }
    }

    public static void lock2() {
        synchronized (objectB) {
            System.out.println(Thread.currentThread() + " locked on ObjectB");

            synchronized (objectA) {
                System.out.println(Thread.currentThread() + " locked on ObjectA");
            }
        }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(ex1::lock1);

        Thread thread2 = new Thread(ex1::lock2);

        thread1.start();
        thread2.start();
    }
}
