package main.java.multithread.chapter4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
    condition.await()：会抛InterruptedException
    condition.awaitUninterruptibly：不会抛异常，因为它不会在等待过程中响应中断
 */
public class RunAwaitUninterruptibly {
    public static void main(String[] args) {
        try {
            AUService auService = new AUService();
            AUThread auThread = new AUThread(auService);
            auThread.start();
            Thread.sleep(3000);
            auThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class AUService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void testMethod() throws InterruptedException {
        try {
            lock.lock();
            System.out.println("wait begin");
//            condition.await();
            condition.awaitUninterruptibly();
            System.out.println("wait end");
        } finally {
            lock.unlock();
        }
    }
}

class AUThread extends Thread {
    private AUService auService;

    AUThread(AUService auService) {
        this.auService = auService;
    }

    @Override
    public void run() {
        try {
            auService.testMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}