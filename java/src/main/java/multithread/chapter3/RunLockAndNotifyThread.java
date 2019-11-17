package main.java.multithread.chapter3;

import java.time.LocalTime;

public class RunLockAndNotifyThread {
    public static void main(String[] args) {
        try {
            Object lock = new Object();
            LockAndNotifyThreadA lockAndNotifyThreadA = new LockAndNotifyThreadA(lock);
            lockAndNotifyThreadA.start();
            Thread.sleep(3000);
            LockAndNotifyThreadB lockAndNotifyThreadB = new LockAndNotifyThreadB(lock);
            lockAndNotifyThreadB.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class LockAndNotifyThreadA extends Thread {
    private Object lock;

    LockAndNotifyThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                System.out.println("start wait time = " + LocalTime.now());
                lock.wait();
                System.out.println("end wait time = " + LocalTime.now());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class LockAndNotifyThreadB extends Thread {
    private Object lock;

    LockAndNotifyThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("start notify time = " + LocalTime.now());
            lock.notify();
            System.out.println("end notify time = " + LocalTime.now());
        }
    }
}