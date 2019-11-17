package main.java.multithread.chapter1;

public class RunIsAliveThread {
    public static void main(String[] args) {
        IsAliveThread isAliveThread = new IsAliveThread();
        System.out.println("begin---" + isAliveThread.isAlive());
        isAliveThread.start();
        //添加sleep一秒，线程对象已经在一秒内执行完毕，isAlive():false
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end---" + isAliveThread.isAlive());
    }
}

class IsAliveThread extends Thread {
    @Override
    public void run() {
        System.out.println("run = " + this.isAlive());
    }
}
