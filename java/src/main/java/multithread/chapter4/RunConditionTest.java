package main.java.multithread.chapter4;

import java.time.LocalTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//调用lock.lock()代码的线程就持有“对象监视器”，其他线程只有等待锁被释放时再次争抢。
public class RunConditionTest {
    public static void main(String[] args) throws InterruptedException {
        ConditionTestService conditionTestService = new ConditionTestService();
        ConditionTestThreadA a = new ConditionTestThreadA(conditionTestService);
        a.setName("A");
        a.start();
        ConditionTestThreadAA aa = new ConditionTestThreadAA(conditionTestService);
        aa.setName("AA");
        aa.start();
        Thread.sleep(100);
        ConditionTestThreadB b = new ConditionTestThreadB(conditionTestService);
        b.setName("B");
        b.start();
        ConditionTestThreadBB bb = new ConditionTestThreadBB(conditionTestService);
        bb.setName("BB");
        bb.start();
    }
}

class ConditionTestService {
    private Lock lock = new ReentrantLock();

    void methodA() {
        try {
            lock.lock();
            System.out.println("methodA begin ThreadName = " + Thread.currentThread().getName() + " timmer = " + LocalTime.now());
            Thread.sleep(5000);
            System.out.println("methodA end ThreadName = " + Thread.currentThread().getName() + " timmer = " + LocalTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void methodB() {
        try {
            lock.lock();
            System.out.println("methodB begin ThreadName = " + Thread.currentThread().getName() + " timmer = " + LocalTime.now());
            Thread.sleep(5000);
            System.out.println("methodB end ThreadName = " + Thread.currentThread().getName() + " timmer = " + LocalTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class ConditionTestThreadA extends Thread {
    private ConditionTestService conditionTestService;

    ConditionTestThreadA(ConditionTestService conditionTestService) {
        this.conditionTestService = conditionTestService;
    }

    @Override
    public void run() {
        conditionTestService.methodA();
    }
}

class ConditionTestThreadAA extends Thread {
    private ConditionTestService conditionTestService;

    ConditionTestThreadAA(ConditionTestService conditionTestService) {
        this.conditionTestService = conditionTestService;
    }

    @Override
    public void run() {
        conditionTestService.methodA();
    }
}

class ConditionTestThreadB extends Thread {
    private ConditionTestService conditionTestService;

    ConditionTestThreadB(ConditionTestService conditionTestService) {
        this.conditionTestService = conditionTestService;
    }

    @Override
    public void run() {
        conditionTestService.methodA();
    }
}

class ConditionTestThreadBB extends Thread {
    private ConditionTestService conditionTestService;

    ConditionTestThreadBB(ConditionTestService conditionTestService) {
        this.conditionTestService = conditionTestService;
    }

    @Override
    public void run() {
        conditionTestService.methodA();
    }
}