package main.java.multithread.chapter2;

public class RunTwoStopThread {
    public static void main(String[] args) {
        TwoStopTrueService twoStopService = new TwoStopTrueService();
        TwoStopThreadA twoStopThreadA = new TwoStopThreadA(twoStopService);
        twoStopThreadA.setName("A");
        twoStopThreadA.start();
        TwoStopThreadB twoStopThreadB = new TwoStopThreadB(twoStopService);
        twoStopThreadB.setName("B");
        twoStopThreadB.start();

    }
}

class TwoStopService {
    //线程B将会被锁死，使用同步代码块来解决这个问题
    synchronized void twoStopMethodA() {
        System.out.println("methodA begin");
        boolean isContinueRun = true;
        while (isContinueRun) {

        }
        System.out.println("methodA end");
    }

    synchronized void twoStopMethodB() {
        System.out.println("methodB begin");
        System.out.println("methodB end");
    }
}

class TwoStopTrueService extends TwoStopService {
    //使用不同的锁对象，并且不是String等缓存对象
    Object obj1 = new Object();

    void twoStopMethodA() {
        synchronized (obj1) {
            System.out.println("methodA begin");
            boolean isContinueRun = true;
            while (isContinueRun) {

            }
            System.out.println("methodA end");
        }
    }

    Object obj2 = new Object();

    void twoStopMethodB() {
        synchronized (obj2) {
            System.out.println("methodB begin");
            System.out.println("methodB end");
        }
    }
}

class TwoStopThreadA extends Thread {
    private TwoStopService twoStopService;

    TwoStopThreadA(TwoStopService twoStopService) {
        this.twoStopService = twoStopService;
    }

    @Override
    public void run() {
        twoStopService.twoStopMethodA();
    }
}

class TwoStopThreadB extends Thread {
    private TwoStopService twoStopService;

    TwoStopThreadB(TwoStopService twoStopService) {
        this.twoStopService = twoStopService;
    }

    @Override
    public void run() {
        twoStopService.twoStopMethodB();
    }
}