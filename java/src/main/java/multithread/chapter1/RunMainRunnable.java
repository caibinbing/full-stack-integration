package main.java.multithread.chapter1;

public class RunMainRunnable {
    public static void main(String[] args) {
        MainRunnable mainRunnable = new MainRunnable();
        Thread thread = new Thread(mainRunnable,"mainRunnable");
        thread.start();
        System.out.println("end.");
    }
}

class MainRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is Running !");
    }
}

