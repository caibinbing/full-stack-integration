package main.java.multithread.chapter3;

//方法notify()一次只随机通知一个线程进行唤醒
//多次调用notify()方法时，会随机将等待状态的线程唤醒，可以唤醒全部waiting中的线程
public class RunNotifyOneThread {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        NotifyOneThreadA notifyOneThreadA = new NotifyOneThreadA(lock);
        notifyOneThreadA.start();
        NotifyOneThreadB notifyOneThreadB = new NotifyOneThreadB(lock);
        notifyOneThreadB.start();
        NotifyOneThreadC notifyOneThreadC = new NotifyOneThreadC(lock);
        notifyOneThreadC.start();
        Thread.sleep(1000);
        NotifyThread notifyThread = new NotifyThread(lock);
        notifyThread.start();
    }
}

class NotifyOneService{
    void NotifyOneMethod(Object lock) {
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

class NotifyOneThreadA extends Thread {
    private Object lock;

    NotifyOneThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyOneService notifyOneService = new NotifyOneService();
        notifyOneService.NotifyOneMethod(lock);
    }
}

class NotifyOneThreadB extends Thread {
    private Object lock;

    NotifyOneThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyOneService notifyOneService = new NotifyOneService();
        notifyOneService.NotifyOneMethod(lock);
    }
}

class NotifyOneThreadC extends Thread {
    private Object lock;

    NotifyOneThreadC(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyOneService notifyOneService = new NotifyOneService();
        notifyOneService.NotifyOneMethod(lock);
    }
}

class NotifyThread extends Thread {
    private Object lock;

    NotifyThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            lock.notify();
            lock.notify();
            lock.notify();
            lock.notify();
            lock.notify();
            lock.notify();
            lock.notify();
        }
    }
}