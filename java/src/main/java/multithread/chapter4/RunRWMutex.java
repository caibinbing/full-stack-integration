package main.java.multithread.chapter4;

import java.time.LocalTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RunRWMutex {
    public static void main(String[] args) {
        RWMutexService rwMutexService = new RWMutexService();
        RWMutexThreadA a = new RWMutexThreadA(rwMutexService);
        a.setName("A");
        RWMutexThreadB b = new RWMutexThreadB(rwMutexService);
        b.setName("B");
        a.start();
        b.start();
    }
}

class RWMutexService {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    void read() {
        try {
            try {
                lock.readLock().lock();
                System.out.println("获得写锁" + Thread.currentThread().getName() + " " + LocalTime.now());
                Thread.sleep(10000);
            } finally {
                lock.readLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void write() {
        try {
            try {
                lock.writeLock().lock();
                System.out.println("获得写锁" + Thread.currentThread().getName() + " " + LocalTime.now());
                Thread.sleep(10000);
            } finally {
                lock.writeLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class RWMutexThreadA extends Thread {
    private RWMutexService rwMutexService;

    RWMutexThreadA(RWMutexService rwMutexService) {
        this.rwMutexService = rwMutexService;
    }

    @Override
    public void run() {
        rwMutexService.read();
    }
}
class RWMutexThreadB extends Thread {
    private RWMutexService rwMutexService;

    RWMutexThreadB(RWMutexService rwMutexService) {
        this.rwMutexService = rwMutexService;
    }

    @Override
    public void run() {
        rwMutexService.write();
    }
}