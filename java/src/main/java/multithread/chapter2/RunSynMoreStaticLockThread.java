package main.java.multithread.chapter2;

import java.time.LocalTime;

public class RunSynMoreStaticLockThread {
    public static void main(String[] args) {
        //虽然是不同对象，但静态的同步方法还是同步运行
        SynMoreStaticLockService synMoreStaticLockServiceA = new SynMoreStaticLockService();
        SynMoreStaticLockService synMoreStaticLockServiceB = new SynMoreStaticLockService();
        SynMoreStaticLockThreadA synMoreStaticLockThreadA = new SynMoreStaticLockThreadA(synMoreStaticLockServiceA);
        synMoreStaticLockThreadA.setName("A");
        synMoreStaticLockThreadA.start();
        SynMoreStaticLockThreadB synMoreStaticLockThreadB = new SynMoreStaticLockThreadB(synMoreStaticLockServiceB);
        synMoreStaticLockThreadB.setName("B");
        synMoreStaticLockThreadB.start();
    }
}

class SynMoreStaticLockService {
    synchronized static void printA() {
        try {
            System.out.println("Thread Name = " + Thread.currentThread().getName()
                    + " at " + LocalTime.now() + " enter printA");
            Thread.sleep(2000);
            System.out.println("Thread Name = " + Thread.currentThread().getName()
                    + " at " + LocalTime.now() + " leave printA");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized static void printB() {
        System.out.println("Thread Name = " + Thread.currentThread().getName()
                + " at " + LocalTime.now() + " enter printB");
        System.out.println("Thread Name = " + Thread.currentThread().getName()
                + " at " + LocalTime.now() + " leave printB");
    }
}
class SynMoreStaticLockThreadA extends Thread {
    private SynMoreStaticLockService synMoreStaticLockService;

    SynMoreStaticLockThreadA(SynMoreStaticLockService synMoreStaticLockService) {
        this.synMoreStaticLockService = synMoreStaticLockService;
    }

    @Override
    public void run() {
        synMoreStaticLockService.printA();
    }
}

class SynMoreStaticLockThreadB extends Thread {
    private SynMoreStaticLockService synMoreStaticLockService;

    SynMoreStaticLockThreadB(SynMoreStaticLockService synMoreStaticLockService) {
        this.synMoreStaticLockService = synMoreStaticLockService;
    }

    @Override
    public void run() {
        synMoreStaticLockService.printB();
    }
}