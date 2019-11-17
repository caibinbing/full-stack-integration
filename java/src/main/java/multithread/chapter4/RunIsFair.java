package main.java.multithread.chapter4;

import java.util.concurrent.locks.ReentrantLock;

//默认情况下ReentrantLock是非公平锁
public class RunIsFair {
    public static void main(String[] args) {
        final IsFairService isFairService = new IsFairService(true);
        Runnable runnable = isFairService::serviceMethod;
        Thread thread = new Thread(runnable);
        thread.start();
        final IsFairService isFairService1 = new IsFairService(false);
        Runnable runnable1 = isFairService1::serviceMethod;
        thread = new Thread(runnable1);
        thread.start();
    }
}

class IsFairService {
    private ReentrantLock lock;

    IsFairService(boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    void serviceMethod() {
        try {
            lock.lock();
            System.out.println("是否公平锁：" + lock.isFair());
        } finally {
            lock.unlock();
        }
    }
}
