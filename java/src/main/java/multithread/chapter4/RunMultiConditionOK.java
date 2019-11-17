package main.java.multithread.chapter4;

import java.time.LocalTime;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunMultiConditionOK {
    public static void main(String[] args) throws InterruptedException {
        MultiConditionOKService multiConditionOKService = new MultiConditionOKService();
        MultiConditionOKThreadA multiConditionOKThreadA = new MultiConditionOKThreadA(multiConditionOKService);
        multiConditionOKThreadA.setName("A");
        multiConditionOKThreadA.start();
        MultiConditionOKThreadB multiConditionOKThreadB = new MultiConditionOKThreadB(multiConditionOKService);
        multiConditionOKThreadB.setName("B");
        multiConditionOKThreadB.start();
        Thread.sleep(3000);
        multiConditionOKService.signalAll_A();
        Thread.sleep(3000);
        multiConditionOKService.signalAll_B();
    }
}

class MultiConditionOKService {
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();

    void awaitA() {
        try {
            lock.lock();
            System.out.println("begin awaitA, time = " + LocalTime.now()
                    + " ThreadName = " + Thread.currentThread().getName());
            conditionA.await();
            System.out.println("end waitA, time = " + LocalTime.now()
                    + " ThreadName = " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void awaitB() {
        try {
            lock.lock();
            System.out.println("begin awaitB, time = " + LocalTime.now()
                    + " ThreadName = " + Thread.currentThread().getName());
            conditionB.await();
            System.out.println("end waitB, time = " + LocalTime.now()
                    + " ThreadName = " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void signalAll_A() {
        try {
            lock.lock();
            System.out.println("signalAll_A, time = " + LocalTime.now()
                    + " ThreadName = " + Thread.currentThread().getName());
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }

    void signalAll_B() {
        try {
            lock.lock();
            System.out.println("signalAll_B, time = " + LocalTime.now()
                    + " ThreadName = " + Thread.currentThread().getName());
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class MultiConditionOKThreadA extends Thread {
    private MultiConditionOKService multiConditionOKService;

    MultiConditionOKThreadA(MultiConditionOKService multiConditionOKService) {
        this.multiConditionOKService = multiConditionOKService;
    }

    @Override
    public void run() {
        multiConditionOKService.awaitA();
    }
}

class MultiConditionOKThreadB extends Thread {
    private MultiConditionOKService multiConditionOKService;

    MultiConditionOKThreadB(MultiConditionOKService multiConditionOKService) {
        this.multiConditionOKService = multiConditionOKService;
    }

    @Override
    public void run() {
        multiConditionOKService.awaitB();
    }
}