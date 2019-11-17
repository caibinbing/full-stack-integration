package main.java.multithread.chapter2;

import java.time.LocalTime;

/*
    异步的原因：持有不同的锁，一个是对象锁，一个是Class锁
    Class锁对类的所有对象实例起作用
 */
public class RunSynTwoLockThread {
    public static void main(String[] args) {
        SynTwoLockService synTwoLockService = new SynTwoLockService();
        SynTwoLockThreadA synTwoLockThreadA = new SynTwoLockThreadA(synTwoLockService);
        synTwoLockThreadA.setName("A");
        synTwoLockThreadA.start();
        SynTwoLockThreadB synTwoLockThreadB = new SynTwoLockThreadB(synTwoLockService);
        synTwoLockThreadB.setName("B");
        synTwoLockThreadB.start();
        SynTwoLockThreadC synTwoLockThreadC = new SynTwoLockThreadC(synTwoLockService);
        synTwoLockThreadC.setName("C");
        synTwoLockThreadC.start();
    }
}

class SynTwoLockService {
    synchronized static void printA() {
        try {
            System.out.println("Thread Name = " + Thread.currentThread().getName()
                    + " at " + LocalTime.now() + " enter printA");
            Thread.sleep(4000);
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

    synchronized static void printC() {
        System.out.println("Thread Name = " + Thread.currentThread().getName()
                + " at " + LocalTime.now() + " enter printC");
        System.out.println("Thread Name = " + Thread.currentThread().getName()
                + " at " + LocalTime.now() + " leave printC");
    }
}

class SynTwoLockThreadA extends Thread {
    private SynTwoLockService synTwoLockService;

    SynTwoLockThreadA(SynTwoLockService synTwoLockService) {
        this.synTwoLockService = synTwoLockService;
    }

    @Override
    public void run() {
        synTwoLockService.printA();
    }
}

class SynTwoLockThreadB extends Thread {
    private SynTwoLockService synTwoLockService;

    SynTwoLockThreadB(SynTwoLockService synTwoLockService) {
        this.synTwoLockService = synTwoLockService;
    }

    @Override
    public void run() {
        synTwoLockService.printB();
    }
}

class SynTwoLockThreadC extends Thread {
    private SynTwoLockService synTwoLockService;

    SynTwoLockThreadC(SynTwoLockService synTwoLockService) {
        this.synTwoLockService = synTwoLockService;
    }

    @Override
    public void run() {
        synTwoLockService.printC();
    }
}