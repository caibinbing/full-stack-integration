package main.java.multithread.chapter2;

import java.time.LocalTime;

public class RunSynStaticMethodThread {
    public static void main(String[] args) {
        SynStaticMethodThreadA synStaticMethodThreadA = new SynStaticMethodThreadA();
        synStaticMethodThreadA.setName("A");
        synStaticMethodThreadA.start();
        SynStaticMethodThreadB synStaticMethodThreadB = new SynStaticMethodThreadB();
        synStaticMethodThreadB.setName("B");
        synStaticMethodThreadB.start();
    }
}

//关键字synchronized应用在static静态方法上，是对当前的*.java文件对应的Class类进行持锁
class SynStaticMethodService {
    synchronized static void printA() {
        try {
            System.out.println("Thread Name = " + Thread.currentThread().getName()
                    + " at " + LocalTime.now() + " enter printA");
            Thread.sleep(3000);
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

class SynStaticMethodThreadA extends Thread {
    @Override
    public void run() {
        SynStaticMethodService.printA();
    }
}

class SynStaticMethodThreadB extends Thread {
    @Override
    public void run() {
        SynStaticMethodService.printB();
    }
}