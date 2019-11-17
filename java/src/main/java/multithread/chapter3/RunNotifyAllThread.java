package main.java.multithread.chapter3;

public class RunNotifyAllThread {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        NotifyAllThreadA notifyAllThreadA = new NotifyAllThreadA(lock);
            notifyAllThreadA.start();
        NotifyAllThreadB notifyAllThreadB = new NotifyAllThreadB(lock);
            notifyAllThreadB.start();
        NotifyAllThreadC notifyAllThreadC = new NotifyAllThreadC(lock);
            notifyAllThreadC.start();
            Thread.sleep(1000);
        NotifyAllThread notifyAllThread = new NotifyAllThread(lock);
            notifyAllThread.start();
    }
}

class NotifyAllService{
    void NotifyAllMethod(Object lock) {
        try{
            synchronized (lock) {
                System.out.println("begin wait() ThreadName = " + Thread.currentThread().getName());
                lock.wait();
                System.out.println("end wait() ThreadName = " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class NotifyAllThreadA extends Thread {
    private Object lock;

    NotifyAllThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyOneService notifyOneService = new NotifyOneService();
        notifyOneService.NotifyOneMethod(lock);
    }
}

class NotifyAllThreadB extends Thread {
    private Object lock;

    NotifyAllThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyAllService notifyAllService = new NotifyAllService();
        notifyAllService.NotifyAllMethod(lock);
    }
}

class NotifyAllThreadC extends Thread {
    private Object lock;

    NotifyAllThreadC(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyAllService notifyAllService = new NotifyAllService();
        notifyAllService.NotifyAllMethod(lock);
    }
}

class NotifyAllThread extends Thread {
    private Object lock;

    NotifyAllThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}