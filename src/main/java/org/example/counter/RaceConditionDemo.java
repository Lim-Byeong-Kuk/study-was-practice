package org.example.counter;


/**
 * 멀티스레드 환경에서 하나의 객체를 공유하게 되면 원치 않는 결과가 발생할 수 있다.
 * RaceCondition 이란?
 * 여러 프로세스 혹은 스레드가 하나의 자원에 접근하기 위해 경쟁하는 상태
 */
public class RaceConditionDemo {

    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        Thread t3 = new Thread(counter, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
