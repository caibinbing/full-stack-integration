package main.java.multithread.chapter1;

public class RunInterruptThread3 {
    public static void main(String[] args) {
        InterruptThread interruptThread = new InterruptThread();
        interruptThread.start();
        try {
            Thread.sleep(2000);
            interruptThread.interrupt();
            //this.isInterrupted()不清除状态标志
            System.out.println("是否停止1？" + interruptThread.isInterrupted());
            System.out.println("是否停止2？" + interruptThread.isInterrupted());
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
