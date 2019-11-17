package main.java.multithread.chapter1;

public class RunCurrentThread {
    public static void main(String[] args) {
        CurrentThread currentThread = new CurrentThread();
        /**
         * Result:
         * Constructor : main
         * run method : Thread-0
         */
        currentThread.start();
        /**
         * Result:
         * Constructor : main
         * run method : main
         */
//        currentThread.run();
    }
}

class CurrentThread extends Thread {
    CurrentThread() {
        System.out.println("Constructor : " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run method : " + Thread.currentThread().getName());
    }
}

