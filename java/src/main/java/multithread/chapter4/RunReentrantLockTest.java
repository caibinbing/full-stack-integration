package main.java.multithread.chapter4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLockService reentrantLockService = new ReentrantLockService();
        ReentrantLockTestThread r1 = new ReentrantLockTestThread(reentrantLockService);
        ReentrantLockTestThread r2 = new ReentrantLockTestThread(reentrantLockService);
        ReentrantLockTestThread r3 = new ReentrantLockTestThread(reentrantLockService);
        ReentrantLockTestThread r4 = new ReentrantLockTestThread(reentrantLockService);
        ReentrantLockTestThread r5 = new ReentrantLockTestThread(reentrantLockService);
        r1.start();
        r2.start();
        r3.start();
        r4.start();
        r5.start();
    }
}

class ReentrantLockService {
    private Lock lock = new ReentrantLock();

    void testMethod() {
        //lock获取锁
        lock.lock();
        for (int i = 0; i < 5; ++i) {
            System.out.println("ThreadName = " + Thread.currentThread().getName() + " " + (i + 1));
        }
        //unlock释放锁
       lock.unlock();
    }
}

class ReentrantLockTestThread extends Thread {
    private ReentrantLockService reentrantLockService;

    ReentrantLockTestThread(ReentrantLockService reentrantLockService) {
        this.reentrantLockService = reentrantLockService;
    }

    @Override
    public void run() {
        reentrantLockService.testMethod();
    }
}