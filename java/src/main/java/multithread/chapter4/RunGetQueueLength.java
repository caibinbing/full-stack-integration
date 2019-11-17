package main.java.multithread.chapter4;

import java.util.concurrent.locks.ReentrantLock;

//getQueueLength()：返回正等待获取锁定的线程估计数
public class RunGetQueueLength {
    public static void main(String[] args) throws InterruptedException {
        final QueueService queueService = new QueueService();
        Runnable runnable = queueService::queueMethod;
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
        Thread.sleep(2000);
        System.out.println("有线程数：" + queueService.lock.getQueueLength() + "正在等待锁");
    }
}

class QueueService {
    ReentrantLock lock = new ReentrantLock();

    void queueMethod() {
        try {
            lock.lock();
            System.out.println("ThreadName = " + Thread.currentThread().getName() + " enter");
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
