package main.java.multithread.chapter4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunSinglePrintCondition {
    public static void main(String[] args) {
        SinglePrintService singlePrintService = new SinglePrintService();
        SinglePrintThreadA singlePrintThreadA = new SinglePrintThreadA(singlePrintService);
        singlePrintThreadA.start();
        SinglePrintThreadB singlePrintThreadB = new SinglePrintThreadB(singlePrintService);
        singlePrintThreadB.start();
    }
}

class SinglePrintService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;

    void set() {
        try {
            lock.lock();
            while (hasValue) {
                condition.await();
            }
            System.out.println("Set");
            hasValue = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void get() {
        try {
            lock.lock();
            while (!hasValue) {
                condition.await();
            }
            System.out.println("GET");
            hasValue = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SinglePrintThreadA extends Thread {
    private SinglePrintService singlePrintService;

    SinglePrintThreadA(SinglePrintService singlePrintService) {
        this.singlePrintService = singlePrintService;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            singlePrintService.set();
        }
    }
}

class SinglePrintThreadB extends Thread {
    private SinglePrintService singlePrintService;

    SinglePrintThreadB(SinglePrintService singlePrintService) {
        this.singlePrintService = singlePrintService;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            singlePrintService.get();
        }
    }
}