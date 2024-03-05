package org.example.counter;

// Counter 는 스레드
public class Counter implements Runnable {

    private int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public int getValue() {
        return count;
    }

    @Override
    public void run() {
        synchronized (this) {
            this.increment();
            System.out.println("value for thread after increment " + Thread.currentThread().getName() + " " + this.getValue());
            this.decrement();
            System.out.println("value for thread after at last " + Thread.currentThread().getName() + " " + this.getValue());
        }
    }
}
