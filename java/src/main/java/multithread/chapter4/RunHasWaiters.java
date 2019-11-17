package main.java.multithread.chapter4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RunHasWaiters {
    public static void main(String[] args) throws InterruptedException {
        final HasWaitersService hasWaitersService = new HasWaitersService();
        Runnable runnable = hasWaitersService::waitMethod;
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; ++i) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; ++i) {
            threads[i].start();
        }
        Thread.sleep(2000);
        hasWaitersService.notifyMethod();
    }
}

class HasWaitersService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void waitMethod() {
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void notifyMethod() {
        try {
            lock.lock();
            System.out.println("有没有线程正在等待condition?"
                    + lock.hasWaiters(condition)
                    + " 线程数是多少？"
                    + lock.getWaitQueueLength(condition));//10
            condition.signal();
            System.out.println("有没有线程正在等待condition?"
                    + lock.hasWaiters(condition)
                    + " 线程数是多少？"
                    + lock.getWaitQueueLength(condition));//9
        }finally {
            lock.unlock();
        }
    }
}
