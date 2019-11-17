package main.java.multithread.chapter3;

import java.time.LocalTime;

//所有线程都呈waiting状态
//假死出现的主要原因是有可能连续唤醒同类。
public class RunAllWaitThread {
    public static void main(String[] args) throws InterruptedException {
        String lock = "";
        AllWaitP allWaitP = new AllWaitP(lock);
        AllWaitC allWaitC = new AllWaitC(lock);
        AllWaitThreadP[] pThreads = new AllWaitThreadP[2];
        AllWaitThreadC[] cThreads = new AllWaitThreadC[2];
        for (int i = 0; i < 2; ++i) {
            pThreads[i] = new AllWaitThreadP(allWaitP);
            pThreads[i].setName("Producer_" + (i + 1));
            cThreads[i] = new AllWaitThreadC(allWaitC);
            cThreads[i].setName("Consumer_" + (i + 1));
            pThreads[i].start();
            cThreads[i].start();
        }
        Thread.sleep(5000);
        //获取线程数：Thread.currentThread().getThreadGroup().activeCount()
        Thread[] threads = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        //the number of threads put into the array
        //除了消费者生产者线程1，2，还有以下线程：
        //main RUNNABLE
        //Monitor Ctrl-Break RUNNABLE
        Thread.currentThread().getThreadGroup().enumerate(threads);
        for (int i = 0; i < threads.length; ++i) {
            System.out.println(threads[i].getName() + " " + threads[i].getState());
        }
    }
}

class AllWaitP {
    private String lock;

    AllWaitP(String lock) {
        this.lock = lock;
    }

    void setValue() {
        try {
            synchronized (lock) {
                while (!AllWaitValueObject.value.equals("")) {
                    System.out.println("Producer " + Thread.currentThread().getName() + " Waiting...");
                    lock.wait();
                }
                System.out.println("Producer " + Thread.currentThread().getName() + " Runnable...");
                String value = LocalTime.now() + "";
                AllWaitValueObject.value = value;
                //此notify不保证唤醒的是异类，可能唤醒同类：生产者唤醒生产者!
                //通知过早！
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class AllWaitC {
    private String lock;

    AllWaitC(String lock) {
        this.lock = lock;
    }

    void getValue() {
        try {
            synchronized (lock) {
                while (AllWaitValueObject.value.equals("")) {
                    System.out.println("Consumer " + Thread.currentThread().getName() + " waiting!!!");
                    lock.wait();
                }
                System.out.println("Consumer " + Thread.currentThread().getName() + " runnable!!!");
                AllWaitValueObject.value = "";
                //此notify不保证唤醒的是异类，可能唤醒同类：生产者唤醒生产者!
                //随机唤醒一个
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class AllWaitThreadP extends Thread {
    private AllWaitP allWaitP;

    AllWaitThreadP(AllWaitP allWaitP) {
        this.allWaitP = allWaitP;
    }

    @Override
    public void run() {
        //不停设置值，如果非空会挂起
        while (true) {
            allWaitP.setValue();
        }
    }
}

class AllWaitThreadC extends Thread {
    private AllWaitC allWaitC;

    AllWaitThreadC(AllWaitC allWaitC) {
        this.allWaitC = allWaitC;
    }

    @Override
    public void run() {
        //不停获取值，如果为空会挂起
        while (true) {
            allWaitC.getValue();
        }
    }
}

class AllWaitValueObject {
    static String value = "";
}