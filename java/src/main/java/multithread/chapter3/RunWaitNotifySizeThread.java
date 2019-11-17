package main.java.multithread.chapter3;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//notify()方法执行后并不立即释放锁。
public class RunWaitNotifySizeThread {
    public static void main(String[] args) {
        try{
            Object lock = new Object();
            WaitNotifySizeThreadA waitNotifySizeThreadA = new WaitNotifySizeThreadA(lock);
            waitNotifySizeThreadA.start();
            Thread.sleep(50);
            WaitNotifySizeThreadB waitNotifySizeThreadB = new WaitNotifySizeThreadB(lock);
            waitNotifySizeThreadB.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WaitNotifySizeList {
    private static List<String> list = new ArrayList<>();

    static void add() {
        list.add("anyString");
    }

    static int size() {
        return list.size();
    }
}

class WaitNotifySizeThreadA extends Thread {
    //锁要是final的
    private final Object lock;

    WaitNotifySizeThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                if (WaitNotifySizeList.size() != 5) {
                    System.out.println("wait begin " + LocalTime.now());
                    lock.wait();
                    //需要线程B执行完，才调用下面句子！
                    System.out.println("wait end " + LocalTime.now());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WaitNotifySizeThreadB extends Thread {
    private final Object lock;

    WaitNotifySizeThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try{
            synchronized (lock) {
                for (int i = 0; i < 10; ++i) {
                    WaitNotifySizeList.add();
                    System.out.println("add " + (i + 1) + " elements");
                    if (WaitNotifySizeList.size() == 5) {
                        lock.notify();
                        System.out.println("Already send message!");
                    }
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
