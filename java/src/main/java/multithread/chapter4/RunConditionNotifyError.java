package main.java.multithread.chapter4;

import java.time.LocalTime;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunConditionNotifyError {
    public static void main(String[] args) throws InterruptedException {
        ConditionNotifyService conditionNotifyService = new ConditionNotifyService();
        ConditionNotifyThreadA conditionNotifyThreadA = new ConditionNotifyThreadA(conditionNotifyService);
        conditionNotifyThreadA.start();
        Thread.sleep(3000);
        conditionNotifyService.signal();
    }
}

class ConditionNotifyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    void await() {
        try {
            lock.lock();
            System.out.println("A, time = " + LocalTime.now());
            //必须在condition.await()方法调用之前调用lock.lock()代码获得同步监视器
            condition.await();
            System.out.println("B, time = " + LocalTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("释放了");
        }
    }

    //解决问题：添加唤醒
    void signal() {
        try {
            lock.lock();
            System.out.println("Signal time = " + LocalTime.now());
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}

class ConditionNotifyThreadA extends Thread {
    private ConditionNotifyService conditionNotifyService;

    ConditionNotifyThreadA(ConditionNotifyService conditionNotifyService) {
        this.conditionNotifyService = conditionNotifyService;
    }

    @Override
    public void run() {
        conditionNotifyService.await();
    }
}