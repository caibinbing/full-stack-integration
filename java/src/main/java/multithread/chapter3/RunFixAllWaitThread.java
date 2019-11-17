package main.java.multithread.chapter3;

import java.time.LocalTime;

//多生产者与多消费者：操作值
//解决了All Wait问题
public class RunFixAllWaitThread {
    public static void main(String[] args) throws InterruptedException {
        String lock = "";
        FixAllWaitP fixAllWaitP = new FixAllWaitP(lock);
        FixAllWaitC fixAllWaitC = new FixAllWaitC(lock);
        FixAllWaitThreadP[] pThreads = new FixAllWaitThreadP[2];
        FixAllWaitThreadC[] cThreads = new FixAllWaitThreadC[2];
        for (int i = 0; i < 2; ++i) {
            pThreads[i] = new FixAllWaitThreadP(fixAllWaitP);
            pThreads[i].setName("Producer_" + (i + 1));
            cThreads[i] = new FixAllWaitThreadC(fixAllWaitC);
            cThreads[i].setName("Consumer_" + (i + 1));
            pThreads[i].start();
            cThreads[i].start();
        }
        Thread.sleep(4000);
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


class FixAllWaitP {
    private String lock;

    FixAllWaitP(String lock) {
        this.lock = lock;
    }

    void setValue() {
        try {
            synchronized (lock) {
                while (!FixAllWaitValueObject.value.equals("")) {
                    System.out.println("Producer " + Thread.currentThread().getName() + " Waiting...");
                    lock.wait();
                }
                System.out.println("Producer " + Thread.currentThread().getName() + " Runnable...");
                String value = LocalTime.now() + "";
                FixAllWaitValueObject.value = value;
                //此notify不保证唤醒的是异类，可能唤醒同类：生产者唤醒生产者!
                //通知过早！
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class FixAllWaitC {
    private String lock;

    FixAllWaitC(String lock) {
        this.lock = lock;
    }

    void getValue() {
        try {
            synchronized (lock) {
                while (FixAllWaitValueObject.value.equals("")) {
                    System.out.println("Consumer " + Thread.currentThread().getName() + " waiting!!!");
                    lock.wait();
                }
                System.out.println("Consumer " + Thread.currentThread().getName() + " runnable!!!");
                FixAllWaitValueObject.value = "";
                //此notify不保证唤醒的是异类，可能唤醒同类：生产者唤醒生产者!
                //使用notifyAll()解决唤醒问题
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class FixAllWaitThreadP extends Thread {
    private FixAllWaitP fixAllWaitP;

    FixAllWaitThreadP(FixAllWaitP fixAllWaitP) {
        this.fixAllWaitP = fixAllWaitP;
    }

    @Override
    public void run() {
        //不停设置值，如果非空会挂起
        while (true) {
            fixAllWaitP.setValue();
        }
    }
}

class FixAllWaitThreadC extends Thread {
    private FixAllWaitC fixAllWaitC;

    FixAllWaitThreadC(FixAllWaitC fixAllWaitC) {
        this.fixAllWaitC = fixAllWaitC;
    }

    @Override
    public void run() {
        //不停获取值，如果为空会挂起
        while (true) {
            fixAllWaitC.getValue();
        }
    }
}

class FixAllWaitValueObject {
    static String value = "";
}

