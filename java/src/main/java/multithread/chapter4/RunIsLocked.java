package main.java.multithread.chapter4;

import java.util.concurrent.locks.ReentrantLock;

public class RunIsLocked {
    public static void main(String[] args) {
        final IsLockedService isLockedService = new IsLockedService(true);
        Runnable runnable = isLockedService::serviceMethod;
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

class IsLockedService {
    private ReentrantLock lock;

    IsLockedService(boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    void serviceMethod() {
        try {
            System.out.println(lock.isLocked());
            lock.lock();
            System.out.println(lock.isLocked());
        }finally {
            lock.unlock();
            System.out.println(lock.isLocked());
        }
    }
}
