package main.java.multithread.chapter4;

import java.time.LocalTime;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunMultiConditionError {
    public static void main(String[] args) throws InterruptedException {
        MultiConditionService multiConditionService = new MultiConditionService();
        MultiConditionThreadA multiConditionThreadA = new MultiConditionThreadA(multiConditionService);
        multiConditionThreadA.setName("A");
        multiConditionThreadA.start();
        MultiConditionThreadB multiConditionThreadB = new MultiConditionThreadB(multiConditionService);
        multiConditionThreadB.setName("B");
        multiConditionThreadB.start();
        Thread.sleep(3000);
        multiConditionService.signalAll();
    }
}

class MultiConditionService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void awaitA() {
        try {
            lock.lock();
            System.out.println("begin awaitA, time = " + LocalTime.now()
                    + " ThreadName = " + Thread.currentThread().getName());
            condition.await();
            System.out.println("end awaitA, time = " + LocalTime.now()
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
            condition.await();
            System.out.println("end awaitB, time = " + LocalTime.now()
                    + " ThreadName = " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /*
        main线程唤醒了AB线程
        单独唤醒线程：先对线程进行分组，然后再唤醒指定组中的线程
     */
    void signalAll() {
        try {
            lock.lock();
            System.out.println("SignalAll, time = " + LocalTime.now()
                    + " ThreadName = " + Thread.currentThread().getName());
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class MultiConditionThreadA extends Thread {
    private MultiConditionService multiConditionService;

    MultiConditionThreadA(MultiConditionService multiConditionService) {
        this.multiConditionService = multiConditionService;
    }

    @Override
    public void run() {
        multiConditionService.awaitA();
    }
}

class MultiConditionThreadB extends Thread {
    private MultiConditionService multiConditionService;

    MultiConditionThreadB(MultiConditionService multiConditionService) {
        this.multiConditionService = multiConditionService;
    }

    @Override
    public void run() {
        multiConditionService.awaitA();
    }
}