package main.java.multithread.chapter3;

public class RunThreadLocalTest {
    public static void main(String[] args) {
        try {
            ThreadLocalTestThreadA threadLocalTestThreadA = new ThreadLocalTestThreadA();
            ThreadLocalTestThreadB threadLocalTestThreadB = new ThreadLocalTestThreadB();
            threadLocalTestThreadA.start();
            threadLocalTestThreadB.start();
            for (int i = 0; i < 100; i++) {
                ThreadLocalTools.threadLocal.set("Main_" + (i + 1));
                System.out.println("Main get Value = " + ThreadLocalTools.threadLocal.get());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadLocalTools {
    static ThreadLocal<String> threadLocal = new ThreadLocal();
}

class ThreadLocalTestThreadA extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                ThreadLocalTools.threadLocal.set("ThreadLocalTestThreadA_" + (i + 1));
                System.out.println("ThreadLocalTestThreadA get Value = " + ThreadLocalTools.threadLocal.get());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadLocalTestThreadB extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                ThreadLocalTools.threadLocal.set("ThreadLocalTestThreadB_" + (i + 1));
                System.out.println("ThreadLocalTestThreadB get Value = " + ThreadLocalTools.threadLocal.get());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}