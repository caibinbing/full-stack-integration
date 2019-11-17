package main.java.multithread.chapter1;

public class RunInterruptedForLoopThread {
    public static void main(String[] args) {
        InterruptForLoopThread interruptForLoopThread = new InterruptForLoopThread();
        interruptForLoopThread.start();
        try {
            Thread.sleep(2000);
            interruptForLoopThread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch InterruptedException");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}

class InterruptForLoopThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 10000000; ++i) {
            if (interrupted()) {
                System.out.println("已经是停止状态了！我要退出了！");
                break;
            }
            System.out.println("i = " + (i + 1));
        }
        //for被中断后，依旧会输出
        System.out.println("我被输出，如果此代码是for又继续运行，线程并未停止");
    }
}