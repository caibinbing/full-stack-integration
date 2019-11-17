package main.java.multithread.chapter4;

import java.util.concurrent.locks.ReentrantLock;

//tryLock()作用是：仅在调用时锁定未被另一个线程保持的情况下，才获取该锁定。
public class RunTryLock {
    public static void main(String[] args) {
        final TryLockService tryLockService = new TryLockService();
        Runnable runnable = tryLockService::waitMethod;
        //A获得锁
        Thread threadA = new Thread(runnable);
        threadA.setName("A");
        threadA.start();
        //B没有获得锁
        Thread threadB = new Thread(runnable);
        threadB.setName("B");
        threadB.start();
    }
}

class TryLockService {
    ReentrantLock lock = new ReentrantLock();

    void waitMethod() {
        if (lock.tryLock()) {
            System.out.println(Thread.currentThread().getName() + "获得锁");
        } else {
            System.out.println((Thread.currentThread().getName() + "没有获得锁"));
        }
    }
}