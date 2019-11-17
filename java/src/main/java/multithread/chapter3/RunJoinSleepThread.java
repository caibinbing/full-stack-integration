package main.java.multithread.chapter3;

import jdk.nashorn.internal.scripts.JO;

import java.time.LocalTime;

public class RunJoinSleepThread {
    public static void main(String[] args) {
        try {
            JoinSleepThreadB joinSleepThreadB = new JoinSleepThreadB();
            JoinSleepThreadA joinSleepThreadA = new JoinSleepThreadA(joinSleepThreadB);
            joinSleepThreadA.start();
            Thread.sleep(1000);
            JoinSleepThreadC joinSleepThreadC = new JoinSleepThreadC(joinSleepThreadB);
            joinSleepThreadC.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class JoinSleepThreadA extends Thread {
    private JoinSleepThreadB joinSleepThreadB;

    JoinSleepThreadA(JoinSleepThreadB joinSleepThreadB) {
        this.joinSleepThreadB = joinSleepThreadB;
    }

    @Override
    public void run() {
        try {
            synchronized (joinSleepThreadB) {
                joinSleepThreadB.start();
                Thread.sleep(6000);
                //Thread.sleep() //不释放锁！
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class JoinSleepThreadB extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("B run begin timer =" + LocalTime.now());
            Thread.sleep(5000);
            System.out.println("B run end timer = " + LocalTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void BService() {
        System.out.println("打印了BService timer = " + LocalTime.now());
    }
}

class JoinSleepThreadC extends Thread {
    private JoinSleepThreadB joinSleepThreadB;

    JoinSleepThreadC(JoinSleepThreadB joinSleepThreadB) {
        this.joinSleepThreadB = joinSleepThreadB;
    }

    @Override
    public void run() {
        joinSleepThreadB.BService();
    }
}
