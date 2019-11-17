package main.java.multithread.chapter1;

public class RunInterruptThread {
    public static void main(String[] args) {
        InterruptThread interruptThread = new InterruptThread();
        interruptThread.start();
        try {
            Thread.sleep(2000);
            interruptThread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch InterruptedException");
            e.printStackTrace();
        }
    }
}

class InterruptThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; ++i) {
            System.out.println("i = " + (i + 1));
        }
    }
}
