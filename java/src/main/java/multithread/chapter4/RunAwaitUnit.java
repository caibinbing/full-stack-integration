package main.java.multithread.chapter4;


import java.time.LocalTime;
import java.util.Calendar;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//线程在等待时间到达前，可以被其他线程提前唤醒
public class RunAwaitUnit {
    public static void main(String[] args) throws InterruptedException {
        //正常情况下10秒后等待结束
        AwaitUnitService awaitUnitService = new AwaitUnitService();
        AwaitUnitThreadA awaitUnitThreadA = new AwaitUnitThreadA(awaitUnitService);
        awaitUnitThreadA.start();
        Thread.sleep(2000);
        AwaitUnitThreadB awaitUnitThreadB = new AwaitUnitThreadB(awaitUnitService);
        awaitUnitThreadB.start();
    }
}

class AwaitUnitService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void waitMethod() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, 10);
            lock.lock();
            System.out.println("wait begin timer = " + LocalTime.now());
            condition.awaitUntil(cal.getTime());
            System.out.println("wait end timer = " + LocalTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void notifyMethod() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, 10);
            lock.lock();
            System.out.println("notify begin timer = " + LocalTime.now());
            condition.signalAll();
            System.out.println("notify end timer = " + LocalTime.now());
        } finally {
            lock.unlock();
        }
    }
}

class AwaitUnitThreadA extends Thread {
    private AwaitUnitService awaitUnitService;

    AwaitUnitThreadA(AwaitUnitService awaitUnitService) {
        this.awaitUnitService = awaitUnitService;
    }

    @Override
    public void run() {
        awaitUnitService.waitMethod();
    }
}

class AwaitUnitThreadB extends Thread {
    private AwaitUnitService awaitUnitService;

    AwaitUnitThreadB(AwaitUnitService awaitUnitService) {
        this.awaitUnitService = awaitUnitService;
    }

    @Override
    public void run() {
        awaitUnitService.notifyMethod();
    }
}