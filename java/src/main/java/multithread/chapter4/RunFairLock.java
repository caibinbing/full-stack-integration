package main.java.multithread.chapter4;

import java.util.concurrent.locks.ReentrantLock;

/*
    公平锁：获取锁的顺序是按照线程加锁的顺序来分配，FIFO
    非公平锁：抢占机制，随机获得锁
 */
public class RunFairLock {
    public static void main(String[] args) {
        //公平锁打印时，基本有序
        //非公平锁运行时，基本乱序，说明：先启动start()不代表先获得锁
        final FairLockService fairLockService = new FairLockService(true);
        Runnable runnable = () -> {
            System.out.println("线程： " + Thread.currentThread().getName()
                    + "运行了");
            fairLockService.FairLockMethod();
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; ++i) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; ++i) {
            threads[i].start();
        }
    }
}

class FairLockService {
    private ReentrantLock lock;

    FairLockService(boolean isFair) {
        this.lock = new ReentrantLock(isFair);
    }

    void FairLockMethod() {
        try {
            lock.lock();
            System.out.println("ThreadName = " + Thread.currentThread().getName()
                    + "获得锁定");
        } finally {
            lock.unlock();
        }
    }
}

