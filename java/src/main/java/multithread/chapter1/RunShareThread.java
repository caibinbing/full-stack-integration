package main.java.multithread.chapter1;

public class RunShareThread {
    public static void main(String[] args) {
        ShareThread shareThread = new ShareThread();
        Thread t1 = new Thread(shareThread, "Thread 1");
        Thread t2 = new Thread(shareThread, "Thread 2");
        Thread t3 = new Thread(shareThread, "Thread 3");
        t1.start();
        t2.start();
        t3.start();
    }
}

class ShareThread extends Thread {
    private int count = 5;

    //synchronized
    @Override
    public synchronized void run() {
        super.run();
        count--;
        System.out.println("Thread : " + currentThread().getName() + " , count = " + count);
    }
}
