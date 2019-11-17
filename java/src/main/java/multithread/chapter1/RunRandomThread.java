package main.java.multithread.chapter1;

public class RunRandomThread {
    public static void main(String[] args) {
        RandomThread randomThread = new RandomThread();
        randomThread.setName("random thread");
        randomThread.start();
        for (int i = 0; i < 10; ++i) {
            int time = (int) (Math.random() * 1000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main = " + Thread.currentThread().getName());
        }
    }
}

class RandomThread extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; ++i) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("run = " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
