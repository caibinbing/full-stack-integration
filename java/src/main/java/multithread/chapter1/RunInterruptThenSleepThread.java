package main.java.multithread.chapter1;

public class RunInterruptThenSleepThread {
    public static void main(String[] args) {
        InterruptThenSleepThread interruptThenSleepThread = new InterruptThenSleepThread();
        interruptThenSleepThread.start();
        interruptThenSleepThread.interrupt();
        System.out.println("end!");
    }
}

class InterruptThenSleepThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 100000; ++i) {
                System.out.println("i = " + (i + 1));
            }
            System.out.println("run begin");
            //先中断，后休眠。以下不会执行。直接catch
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("先停止，再遇到了sleep!进入catch!");
            e.printStackTrace();
        }
    }
}
