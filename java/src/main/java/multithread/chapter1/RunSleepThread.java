package main.java.multithread.chapter1;

/**
 * run:main
 * start:Thread-0
 */
public class RunSleepThread {
    public static void main(String[] args) {
        SleepThread sleepThread = new SleepThread();
        System.out.println("begin = " + System.currentTimeMillis());
        sleepThread.start();
        System.out.println("end = " + System.currentTimeMillis());
    }
}

class SleepThread extends Thread {
    @Override
    public void run() {
        System.out.println("run threadName = " + currentThread().getName() + " begin");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("run threadName = " + currentThread().getName() + " end");
    }
}
