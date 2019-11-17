package main.java.multithread.chapter3;

import java.time.LocalTime;

/*
    本例：1个生产者，1个消费者，交替输出
    如果设计多生产者，多消费者，运行可能造成假死，所有线程呈waiting状态。
 */
public class RunOnePOneCThread {
    public static void main(String[] args) {
        String lock = "";
        OneP oneP = new OneP(lock);
        OneC oneC = new OneC(lock);
        ThreadP threadP = new ThreadP(oneP);
        ThreadC threadC = new ThreadC(oneC);
        threadP.start();
        threadC.start();
    }
}

class OneP {
    private String lock;

    OneP(String lock) {
        this.lock = lock;
    }

    void setValue() {
        try {
            synchronized (lock) {
                if (!OnePOneCValueObject.value.equals("")) {
                    lock.wait();
                }
                String value = LocalTime.now() + "";
                System.out.println("set value: " + value);
                OnePOneCValueObject.value = value;
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class OneC {
    private String lock;

    OneC(String lock) {
        this.lock = lock;
    }

    void getValue() {
        try {
            synchronized (lock) {
                if (OnePOneCValueObject.value.equals("")) {
                    lock.wait();
                }
                System.out.println("get value:" + OnePOneCValueObject.value);
                OnePOneCValueObject.value = "";
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class OnePOneCValueObject {
    static String value = "";
}

class ThreadP extends Thread {
    private OneP oneP;

    ThreadP(OneP oneP) {
        this.oneP = oneP;
    }

    @Override
    public void run() {
        while (true) {
            oneP.setValue();
        }
    }
}

class ThreadC extends Thread {
    private OneC oneC;

    ThreadC(OneC oneC) {
        this.oneC = oneC;
    }

    @Override
    public void run() {
        while (true) {
            oneC.getValue();
        }
    }
}