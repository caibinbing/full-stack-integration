package main.java.multithread.chapter1;

public class RunThrowInterruptExceptionThread {
    public static void main(String[] args) {
        try {
            ThrowInterruptExceptionThread throwInterruptExceptionThread = new ThrowInterruptExceptionThread();
            throwInterruptExceptionThread.start();
            Thread.sleep(2000);
            throwInterruptExceptionThread.interrupt();
        } catch (InterruptedException e) {
            //catch主线程的异常。Thread.sleep()是主线程休眠
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}

class ThrowInterruptExceptionThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 10000000; ++i) {
                if (interrupted()) {
                    System.out.println("已经是停止状态了！我要退出了！");
                    throw new InterruptedException();
                }
                System.out.println("i = " + (i + 1));
            }
            System.out.println("under for loop");
        } catch (InterruptedException e) {
            System.out.println("进入catch");
            e.printStackTrace();
        }
    }
}

