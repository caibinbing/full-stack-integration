package main.java.multithread.chapter2;

import java.util.concurrent.atomic.AtomicLong;

public class RunAtomicIntegerNotSafeThread {
    public static void main(String[] args) {
        try {
            AtomicIntegerNotSafeService atomicIntegerNotSafeService = new AtomicIntegerNotSafeService();
            AtomicIntegerNotSafeThread[] threads = new AtomicIntegerNotSafeThread[5];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new AtomicIntegerNotSafeThread(atomicIntegerNotSafeService);
            }
            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
            }
            Thread.sleep(1000);
            System.out.println(atomicIntegerNotSafeService.aiRef.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class AtomicIntegerNotSafeService {
    static AtomicLong aiRef = new AtomicLong();

    //addAndGet方法是原子的，但方法和方法之间的调用却不是原子的
    //解决这样的问题必须要用同步
    synchronized void addNum() {
        System.out.println(Thread.currentThread().getName() + "加了100之后的值是 " + aiRef.addAndGet(100));
        aiRef.addAndGet(1);
    }
}

class AtomicIntegerNotSafeThread extends Thread {
    private AtomicIntegerNotSafeService atomicIntegerNotSafeService;

    AtomicIntegerNotSafeThread(AtomicIntegerNotSafeService atomicIntegerNotSafeService) {
        this.atomicIntegerNotSafeService = atomicIntegerNotSafeService;
    }

    @Override
    public void run() {
        atomicIntegerNotSafeService.addNum();
    }
}