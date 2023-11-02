package org.example;

public class TicTak implements Runnable{

    private final String bracket;

    public TicTak(String bracket) {
        this.bracket = bracket;
    }

    @Override
    public void run() {
        while (true){
            System.out.print(bracket);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
