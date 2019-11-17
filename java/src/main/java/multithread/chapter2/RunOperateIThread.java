package main.java.multithread.chapter2;

public class RunOperateIThread {
    public static void main(String[] args) {
        OperateIThread operateIThread = new OperateIThread();
        operateIThread.start();
    }
}

class Main {
    int i = 10;
    synchronized void operateIMainMethod() {
        try {
            i--;
            System.out.println("main print i = " + i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Sub extends Main {
    synchronized void operateISubMethod() {
        try {
            while (i > 0) {
                i--;
                System.out.println("sub print i = " + i);
                Thread.sleep(100);
                //子类可以通过“可重入锁”调用父类的同步方法
                this.operateIMainMethod();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class OperateIThread extends Thread {
    @Override
    public void run() {
        Sub sub = new Sub();
        sub.operateISubMethod();
    }
}