package main.java.multithread.chapter1;

public class SuspendDeadLock {
    public static void main(String[] args) {
        try{

            final SynchronizedObj synchronizedObj = new SynchronizedObj();
            Thread thread1 = new Thread(synchronizedObj::printString);
            thread1.setName("Thread-a");
            thread1.start();
            Thread.sleep(1000);
            Thread thread2 = new Thread(() -> {
                System.out.println("thread2启动了，但进不了printString()方法");
                System.out.println("因为printString()方法被a线程锁定并且永远suspend暂停了！");
                synchronizedObj.printString();
            });
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class SynchronizedObj{
    synchronized void printString() {
        System.out.println("begin");
        if ("Thread-a".equals(Thread.currentThread().getName())) {
            System.out.println("a线程永远suspend了！");
            Thread.currentThread().suspend();
        }
        System.out.println("end");
    }
}