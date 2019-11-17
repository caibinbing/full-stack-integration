package main.java.multithread.chapter3;

import java.time.LocalTime;

/*
    notify()执行后，不释放锁
    必须执行完notify()所在的同步synchronized代码块后才释放锁
 */
public class RunNotifyHoldLockThread {
    public static void main(String[] args) {
        Object lock = new Object();
        NotifyHoldLockThreadA notifyHoldLockThreadA = new NotifyHoldLockThreadA(lock);
        notifyHoldLockThreadA.start();
        NotifyHoldLockThreadB notifyHoldLockThreadB = new NotifyHoldLockThreadB(lock);
        notifyHoldLockThreadB.start();
        NotifyHoldLockThreadC notifyHoldLockThreadC = new NotifyHoldLockThreadC(lock);
        notifyHoldLockThreadC.start();
    }
}

class NotifyHoldLockService {
    void notifyHoldLockMethod(Object lock) {
        try{
            synchronized (lock) {
                System.out.println("begin wait() ThreadName = "+Thread.currentThread().getName());
                lock.wait();
                System.out.println("end wait() ThreadName = " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void synNotifyMethod(Object lock) {
        try{
            synchronized (lock) {
                System.out.println("begin notify() ThreadName = " + Thread.currentThread().getName() + " time = " + LocalTime.now());
                lock.notify();
                Thread.sleep(5000);
                System.out.println("end notify() ThreadName = " + Thread.currentThread().getName() + " time = " + LocalTime.now());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class NotifyHoldLockThreadA extends Thread {
    private Object lock;

    NotifyHoldLockThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyHoldLockService notifyHoldLockService = new NotifyHoldLockService();
        notifyHoldLockService.notifyHoldLockMethod(lock);
    }
}

class NotifyHoldLockThreadB extends Thread {
    private Object lock;

    NotifyHoldLockThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyHoldLockService notifyHoldLockService = new NotifyHoldLockService();
        notifyHoldLockService.synNotifyMethod(lock);
    }
}

class NotifyHoldLockThreadC extends Thread {
    private Object lock;

    NotifyHoldLockThreadC(Object lock) {

        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyHoldLockService notifyHoldLockService = new NotifyHoldLockService();
        notifyHoldLockService.synNotifyMethod(lock);
    }
}