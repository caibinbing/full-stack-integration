package main.java.multithread.chapter1;

public class RunThreadDeathThread {
    public static void main(String[] args) {
        ThreadDeathThread threadDeathThread = new ThreadDeathThread();
        threadDeathThread.start();
    }
}

class ThreadDeathThread extends Thread {
    @Override
    public void run() {
        try {
            this.stop();
        } catch (ThreadDeath threadDeath) {
            System.out.println("enter catch()");
            threadDeath.printStackTrace();
        }
    }
}

