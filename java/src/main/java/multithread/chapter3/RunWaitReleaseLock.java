package main.java.multithread.chapter3;

public class RunWaitReleaseLock {
    public static void main(String[] args) {
        Object lock = new Object();
        WaitReleaseLockThreadA waitReleaseLockThreadA = new WaitReleaseLockThreadA(lock);
        waitReleaseLockThreadA.start();
        WaitReleaseLockThreadB waitReleaseLockThreadB = new WaitReleaseLockThreadB(lock);
        waitReleaseLockThreadB.start();
    }
}

class WaitReleaseLockService {
    void WaiReleaseLockMethod(Object lock) {
        try{
            synchronized (lock) {
                System.out.println("begin wait()");
//                lock.wait();
                //改成sleep()就成了同步的效果
                Thread.sleep(1000);
                System.out.println("end wait()");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WaitReleaseLockThreadA extends Thread {
    private Object lock;

    WaitReleaseLockThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        WaitReleaseLockService waitReleaseLockService = new WaitReleaseLockService();
        waitReleaseLockService.WaiReleaseLockMethod(lock);
    }
}

class WaitReleaseLockThreadB extends Thread {
    private Object lock;

    WaitReleaseLockThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        WaitReleaseLockService waitReleaseLockService = new WaitReleaseLockService();
        waitReleaseLockService.WaiReleaseLockMethod(lock);
    }
}