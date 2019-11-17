package main.java.multithread.chapter3;

import java.time.LocalDate;

public class RunIsolationTest {
    public static void main(String[] args) {
        try {
            IsolationTestThreadA isolationTestThreadA = new IsolationTestThreadA();
            isolationTestThreadA.start();
            Thread.sleep(1000);
            IsolationTestThreadB isolationTestThreadB = new IsolationTestThreadB();
            isolationTestThreadB.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class IsolationTestTools {
    static ThreadLocal<LocalDate> threadLocal = new ThreadLocal<>();
}

class IsolationTestThreadA extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                if (IsolationTestTools.threadLocal.get() == null) {
                    IsolationTestTools.threadLocal.set(LocalDate.now());
                }
                System.out.println("A " + IsolationTestTools.threadLocal.get().getDayOfMonth());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class IsolationTestThreadB extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                if (IsolationTestTools.threadLocal.get() == null) {
                    IsolationTestTools.threadLocal.set(LocalDate.now());
                }
                System.out.println("B " + IsolationTestTools.threadLocal.get().getDayOfMonth());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}