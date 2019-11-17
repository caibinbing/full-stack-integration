package main.java.multithread.chapter1;

public class RunReturnInterruptThread {
    public static void main(String[] args) {
        ReturnInterruptThread returnInterruptThread = new ReturnInterruptThread();
        returnInterruptThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        returnInterruptThread.interrupt();
    }
}

class ReturnInterruptThread extends Thread {
    @Override
    public void run() {
        while (true) {
            if (isInterrupted()) {
                System.out.println("stop!");
                return;
            }
            System.out.println("timer = " + System.currentTimeMillis());
        }
    }
}

