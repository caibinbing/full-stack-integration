package main.java.multithread.chapter1;

public class RunInterruptThread2 {
    public static void main(String[] args) {
        InterruptThread interruptThread = new InterruptThread();
        interruptThread.start();
        try {
            Thread.sleep(2000);
            interruptThread.interrupt();
            //this.interrupted()清除状态标志
            System.out.println("是否停止1？ " + Thread.interrupted());
            System.out.println("是否停止2？ " + Thread.interrupted());
        } catch (InterruptedException e) {
            System.out.println("main catch InterruptedException");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
