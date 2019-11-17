package main.java.multithread.chapter3;

import java.time.LocalTime;

//P190 没有join方法时，main end往往都是第一个打印的
public class RunJoinMoreThread {
    public static void main(String[] args) {
        try {
            JoinMoreThreadB joinMoreThreadB = new JoinMoreThreadB();
            JoinMoreThreadA joinMoreThreadA = new JoinMoreThreadA(joinMoreThreadB);
            joinMoreThreadA.start();
            joinMoreThreadB.start();
            joinMoreThreadB.join(2000);
            System.out.println("main end " + LocalTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class JoinMoreThreadA extends Thread {
    JoinMoreThreadB joinSleepThreadB = new JoinMoreThreadB();

    JoinMoreThreadA(JoinMoreThreadB joinSleepThreadB) {
        this.joinSleepThreadB = joinSleepThreadB;
    }

    @Override
    public void run() {
        try {
            synchronized (joinSleepThreadB) {
                System.out.println("begin A ThreadName = " + Thread.currentThread().getName() + " time = " + LocalTime.now());
                Thread.sleep(5000);
                System.out.println("end A ThreadName = " + Thread.currentThread().getName() + " time = " + LocalTime.now());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class JoinMoreThreadB extends Thread {
    @Override
    synchronized public void run() {
        try {
            System.out.println("begin B ThreadName = " + Thread.currentThread().getName() + " time = " + LocalTime.now());
            Thread.sleep(5000);
            System.out.println("end B ThreadName = " + Thread.currentThread().getName() + " time = " + LocalTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}