package main.java.multithread.chapter1;

public class RunDeamonThread {
    public static void main(String[] args) {
        try {
            DaemonThread daemonThread = new DaemonThread();
            daemonThread.setDaemon(true);
            daemonThread.start();
            //5秒后主线程执行输出，并结束
            Thread.sleep(5000);
            System.out.println("我离开了demonThread对象也不再打印了，也就是停止了！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class DaemonThread extends Thread {
    private int i = 0;

    @Override
    public void run() {
        try {
            while (true) {
                ++i;
                System.out.println("i = " + (i));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}