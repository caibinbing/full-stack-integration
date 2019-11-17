package main.java.multithread.chapter4;

import java.util.concurrent.locks.ReentrantLock;

public class RunGetHoldCount {
    public static void main(String[] args) {
        LockService lockService = new LockService();
        lockService.getHoldCountMethod();
    }
}

class LockService {
    private ReentrantLock lock = new ReentrantLock();
    //getHoldCount()：查询当前线程保持此锁定的个数，即调用lock()的次数
    void getHoldCountMethod() {
        try {
            lock.lock();
            System.out.println("LockService getHoldCount = " + lock.getHoldCount());
            getHoldCountMethod2();
        }finally {
            lock.unlock();
        }
    }

    void getHoldCountMethod2() {
        try {
            lock.lock();
            System.out.println("LockService getHoldCount2 = " + lock.getHoldCount());
        }finally {
            lock.unlock();
        }
    }
}