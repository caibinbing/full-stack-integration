package main.java.multithread.chapter1;

public class RunStopThread {
    public static void main(String[] args) {
        try {
            StopThread stopThread = new StopThread();
            stopThread.start();
            Thread.sleep(8000);
            stopThread.stop();
            System.out.println("end!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class StopThread extends Thread {
    private int i = 0;

    @Override
    public void run() {
        try {
            while (true) {
                i++;
                System.out.println("i = " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
