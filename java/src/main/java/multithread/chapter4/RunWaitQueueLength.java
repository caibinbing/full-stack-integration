package main.java.multithread.chapter4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//getWaitQueueLength(condition)：返回等待与此锁定相关的给定条件condition的线程估计数
public class RunWaitQueueLength {
    public static void main(String[] args) throws InterruptedException {
        final WaitQueueService waitQueueService = new WaitQueueService();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                waitQueueService.waitQueueMethod();
            }
        };
        Thread[] threads = new Thread[9];
        for (int i = 0; i < 9; ++i) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 9; ++i) {
            threads[i].start();
        }
        Thread.sleep(2000);
        waitQueueService.notifyMethod();
    }
}

class WaitQueueService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition newCondition = lock.newCondition();

    void waitQueueMethod() {
        try {
            lock.lock();
            newCondition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void notifyMethod() {
        try {
            lock.lock();
            System.out.println("有" + lock.getWaitQueueLength(newCondition)
                    + "个线程正在等待newCondition");
            newCondition.signal();
        } finally {
            lock.unlock();
        }
    }
}

