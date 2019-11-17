package main.java.multithread.chapter1;

/**
 * Error:
 * i = 4 threadName = Thread-1
 * i = 3 threadName = Thread-5
 * i = 4 threadName = Thread-2
 * i = 2 threadName = Thread-3
 * i = 1 threadName = Thread-4
 */
public class RunPrintlnThread {
    public static void main(String[] args) {
        PrintlnThread printlnThread = new PrintlnThread();
        Thread t1 = new Thread(printlnThread);
        Thread t2 = new Thread(printlnThread);
        Thread t3 = new Thread(printlnThread);
        Thread t4 = new Thread(printlnThread);
        Thread t5 = new Thread(printlnThread);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}

class PrintlnThread extends Thread {
    private int i = 5 ;

    @Override
    public void run() {
        System.out.println("i = " + (--i) + " threadName = " + Thread.currentThread().getName());
    }
}
