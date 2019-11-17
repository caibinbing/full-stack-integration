package main.java.multithread.chapter1;

public class RunInterruptSleepThread {
    public static void main(String[] args) {
        InterruptSleepThread interruptSleepThread = new InterruptSleepThread();
        interruptSleepThread.start();
        try{
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        interruptSleepThread.interrupt();
        System.out.println("end!");
    }
}

class InterruptSleepThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("run begin");
        try {
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            //在线程sleep()时，调用interrupt()会清除停止状态值！
            System.out.println("在沉睡中被停止！进入catch： " + this.isInterrupted());
            e.printStackTrace();
        }
    }
}
