package main.java.multithread.chapter4;

import java.time.LocalTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//读读共享
public class RunRRShare {
    public static void main(String[] args) {
        //两个线程几乎同时进入Lock方法后的代码。
        RRShareService rrShareService = new RRShareService();
        ThreadA a = new ThreadA(rrShareService);
        a.setName("A");
        ThreadB b = new ThreadB(rrShareService);
        b.setName("B");
        a.start();
        b.start();
    }
}

class RRShareService {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    void read() {
        try {
            try {
                lock.readLock().lock();
                System.out.println("获得读锁" + Thread.currentThread().getName() + " " + LocalTime.now());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.readLock().unlock();
        }
    }
}

class ThreadA extends Thread {
    private RRShareService rrShareService;

    ThreadA(RRShareService rrShareService) {
        this.rrShareService = rrShareService;
    }

    @Override
    public void run() {
        rrShareService.read();
    }
}

class ThreadB extends Thread {
    private RRShareService rrShareService;

    ThreadB(RRShareService rrShareService) {
        this.rrShareService = rrShareService;
    }

    @Override
    public void run() {
        rrShareService.read();
    }
}