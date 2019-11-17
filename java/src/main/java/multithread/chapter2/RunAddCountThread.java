package main.java.multithread.chapter2;

import java.util.concurrent.atomic.AtomicInteger;

public class RunAddCountThread {
    public static void main(String[] args) {
        AddCountThread addCountThread = new AddCountThread();
        Thread t1 = new Thread(addCountThread);
        t1.start();
        Thread t2 = new Thread(addCountThread);
        t2.start();
        Thread t3 = new Thread(addCountThread);
        t3.start();
        Thread t4 = new Thread(addCountThread);
        t4.start();
        Thread t5 = new Thread(addCountThread);
        t5.start();
    }
}

class AddCountThread extends Thread {
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            //底层实现是CAS
            System.out.println(count.incrementAndGet());
        }
    }
}
