package main.java.multithread.chapter3;

import java.time.LocalTime;

//带一个参数的wait(long)方法的功能是等待某一时间内是否有线程对锁进行唤醒，如果超过这个时间则自动唤醒
public class RunWaitHasParamMethod {
    private static Object lock = new Object();
    private static Runnable runnable = () -> {
        try {
            synchronized (lock) {
                System.out.println("wait begin timer = " + LocalTime.now());
                lock.wait(5000);
                System.out.println("wait end timer = " + LocalTime.now());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };
    private static Runnable runnable1 = () -> {
        synchronized (lock) {
            System.out.println("notify begin timer = " + LocalTime.now());
            lock.notify();
            System.out.println("notify end timer = " + LocalTime.now());
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(runnable);
        t.start();
        Thread.sleep(3000);
        Thread t2 = new Thread(runnable1);
        t2.start();
    }
}