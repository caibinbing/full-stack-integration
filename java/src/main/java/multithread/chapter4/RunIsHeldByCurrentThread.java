package main.java.multithread.chapter4;

import java.util.concurrent.locks.ReentrantLock;

//isHeldByCurrentThread()：查询当前线程是否保持此锁定。
public class RunIsHeldByCurrentThread {
    public static void main(String[] args) {
        final IsHeldService isHeldService = new IsHeldService(true);
        Runnable runnable = isHeldService::serviceMethod;
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

class IsHeldService {
    private ReentrantLock lock;

    IsHeldService(boolean isFair) {
        this.lock = new ReentrantLock(isFair);
    }

    void serviceMethod() {
        try {
            System.out.println(lock.isHeldByCurrentThread());
            lock.lock();
            System.out.println(lock.isHeldByCurrentThread());
        }finally {
            lock.unlock();
        }
    }
}
