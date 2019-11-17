package main.java.multithread.chapter4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//hasQueuedThread查询指定的线程是否正在等待获取此锁定
// hasQueuedThreads查询是否有线程正在等待获取此锁定
public class RunHasQueuedThread {
    public static void main(String[] args) throws InterruptedException {
        final HasQueuedThreadService hasQueuedThreadService = new HasQueuedThreadService();
        Runnable runnable = hasQueuedThreadService::waitMethod;
        Thread threadA = new Thread(runnable);
        threadA.start();
        Thread.sleep(500);
        Thread threadB = new Thread(runnable);
        threadB.start();
        Thread.sleep(500);
        System.out.println(hasQueuedThreadService.lock.hasQueuedThread(threadA));//false
        System.out.println(hasQueuedThreadService.lock.hasQueuedThread(threadB));//true
        System.out.println(hasQueuedThreadService.lock.hasQueuedThreads());//true

    }
}

class HasQueuedThreadService {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    void waitMethod() {
        try {
            lock.lock();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
