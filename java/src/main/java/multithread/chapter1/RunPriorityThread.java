package main.java.multithread.chapter1;

public class RunPriorityThread {
    public static void main(String[] args) {
        System.out.println("main thread begin priority = " + Thread.currentThread().getPriority());
        System.out.println("main thread end priority = " + Thread.currentThread().getPriority());
        /*
             Output:
             main thread begin priority = 5
             main thread end priority = 5
             Thread1 run priority = 6
             Thread2 run priority = 6
         */
        Thread.currentThread().setPriority(6);
        System.out.println(Thread.currentThread().getName());   //main
        PriorityThread1 thread1 = new PriorityThread1();
        thread1.start();
    }
}

class PriorityThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("Thread1 run priority = " + getPriority());
        PriorityThread2 thread2 = new PriorityThread2();
        thread2.start();
    }
}

class PriorityThread2 extends Thread{
    @Override
    public void run() {
        System.out.println("Thread2 run priority = " + getPriority());
    }
}