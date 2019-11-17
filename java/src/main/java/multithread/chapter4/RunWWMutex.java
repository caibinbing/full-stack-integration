package main.java.multithread.chapter4;

import java.time.LocalTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//写写互斥
//写锁代码LockwriteLock()：同一时间只允许一个线程执行lock()方法后面的代码
public class RunWWMutex {
    public static void main(String[] args) {
        WWMutexService wwMutexService = new WWMutexService();
        WWMutexThreadA a = new WWMutexThreadA(wwMutexService);
        a.setName("A");
        WWMutexThreadB b = new WWMutexThreadB(wwMutexService);
        b.setName("B");
        a.start();
        b.start();
    }
}

class WWMutexService {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    void write() {
        try {
            try {
                lock.writeLock().lock();
                System.out.println("获得写锁 " + Thread.currentThread().getName() + " " + LocalTime.now());
                Thread.sleep(10000);
            } finally {
                lock.writeLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WWMutexThreadA extends Thread {
    private WWMutexService wwMutexService;

    WWMutexThreadA(WWMutexService wwMutexService) {
        this.wwMutexService = wwMutexService;
    }

    @Override
    public void run() {
        wwMutexService.write();
    }
}


class WWMutexThreadB extends Thread {
    private WWMutexService wwMutexService;

    WWMutexThreadB(WWMutexService wwMutexService) {
        this.wwMutexService = wwMutexService;
    }

    @Override
    public void run() {
        wwMutexService.write();
    }
}
