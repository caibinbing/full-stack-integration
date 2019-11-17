package main.java.multithread.chapter3;

import java.util.ArrayList;
import java.util.List;

public class RunWaitOldThread {
    public static void main(String[] args) throws InterruptedException {
        String lock = "";
        Add add = new Add(lock);
        Subtract subtract = new Subtract(lock);
        WaitOldThreadB subtractThreadA = new WaitOldThreadB(subtract);
        subtractThreadA.setName("subtractThreadA");
        subtractThreadA.start();
        WaitOldThreadB subtractThreadB = new WaitOldThreadB(subtract);
        subtractThreadB.setName("subtractThreadB");
        subtractThreadB.start();
        Thread.sleep(1000);
        WaitOldThreadA addThread = new WaitOldThreadA(add);
        addThread.setName("addThread");
        addThread.start();
    }
}

class Add {
    private String lock;

    Add(String lock) {
        this.lock = lock;
    }

    void add() {
        synchronized (lock) {
            ValueObject.list.add("anyString");
            lock.notifyAll();
        }
    }
}

class Subtract {
    private String lock;

    Subtract(String lock) {
        this.lock = lock;
    }

    void subtract() {
        try {
            synchronized (lock) {
                //使用if会出现数组越界的情况。
                //if改成while。但使用while无法自动停止。
                while (ValueObject.list.size() == 0) {
                    System.out.println("wait begin ThreadName = " + Thread.currentThread().getName());
                    lock.wait();
                    System.out.println("wait end Thread = " + Thread.currentThread().getName());
                }
                ValueObject.list.remove(0);
                System.out.println("list size = " + ValueObject.list.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ValueObject {
    static List list = new ArrayList<>();
}

class WaitOldThreadA extends Thread {
    private Add add;

    WaitOldThreadA(Add add) {
        this.add = add;
    }

    @Override
    public void run() {
        add.add();
    }
}
class WaitOldThreadB extends Thread {
    private Subtract subtract;

    WaitOldThreadB(Subtract subtract) {
        this.subtract = subtract;
    }

    @Override
    public void run() {
        subtract.subtract();
    }
}