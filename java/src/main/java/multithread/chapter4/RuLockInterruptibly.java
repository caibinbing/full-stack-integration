package main.java.multithread.chapter4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//lockInterruptibly()作用：如果当前线程未被中断，则获得锁定，如果已经被中断则出现异常
public class RuLockInterruptibly {
    public static void main(String[] args) {
        final LockInterruptiblyService lockInterruptiblyService = new LockInterruptiblyService();
        Runnable runnable = lockInterruptiblyService::waitMethod;
        Thread threadA = new Thread(runnable);
        threadA.setName("A");
        threadA.start();
        Thread threadB = new Thread(runnable);
        threadB.setName("B");
        threadB.start();
        threadB.interrupt();//打标记
        System.out.println("main end");
    }
}


class LockInterruptiblyService {
    ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void waitMethod() {
        try {
            //使用lockInterruptibly()会报异常
            lock.lockInterruptibly();
            System.out.println("lock begin " + Thread.currentThread().getName());
            for (int i = 0; i < Integer.MAX_VALUE / 10; i++) {
                String s = new String();
                Math.random();
            }
            System.out.println("lock end " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}