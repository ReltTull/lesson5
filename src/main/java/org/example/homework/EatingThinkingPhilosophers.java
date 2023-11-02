package org.example.homework;

import java.util.concurrent.Semaphore;

/**
 Есть пять философов (потоки), которые могут либо обедать (выполнение кода) либо размышлять (ожидание).

 ● Каждый философ должен пообедать три раза. Каждый прием пищи длиться 500 миллисекунд
 ● После каждого приема пищи философ должен размышлять
 ● Не должно возникнуть общей блокировки
 ● Философы не должны есть больше одного раза подряд
 */

public class EatingThinkingPhilosophers {
    private static final int NUM_PHILOSOPHERS = 5;
    /**
     * кол-во вилок определяет, сколько философов могут одновременно есть
     */
    private static final int NUM_FORKS = 2;
    private static final Semaphore[] forks = new Semaphore[NUM_FORKS];
    private static final Semaphore eatingLimit = new Semaphore(NUM_PHILOSOPHERS / 2);

    static {
        for (int i = 0; i < NUM_FORKS; i++) {
            forks[i] = new Semaphore(1);
        }
    }

    public static void main(String[] args) {
        Thread[] philosophers = new Thread[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Thread(new Philosopher(i));
            philosophers[i].start();
        }
    }

    static class Philosopher implements Runnable {
        private final int id;
        /**
         * определяет, сколько раз ел филисоф
         */
        private int eatingCount = 0;

        public Philosopher(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (eatingCount < 3) {
                try {
                    thinking();
                    eating();
                    eatingCount++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void thinking() throws InterruptedException {
            System.out.println("Philosopher " + id + " is thinking");
            Thread.sleep( (long) (Math.random() * 1000));
        }

        private void eating() throws InterruptedException {
            eatingLimit.acquire();
            forks[id % NUM_FORKS].acquire(); 
            forks[(id + 1) % NUM_FORKS].acquire();

            System.out.println("Philosopher " + id + " is eating");
            Thread.sleep(500);

            forks[(id + 1) % NUM_FORKS].release(); // Put down right fork
            forks[id % NUM_FORKS].release(); // Put down left fork
            eatingLimit.release(); // Release the eating limit
        }
    }
}

