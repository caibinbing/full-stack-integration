package main.java.multithread.chapter2;

public class RunSynLockInThread {
    public static void main(String[] args) {
        SynLockInThread synLockInThread = new SynLockInThread();
        synLockInThread.start();
    }
}

class Services {
    //可重入锁
    synchronized void service1() {
        System.out.println("service1");
        service2();
    }

    private synchronized void service2() {
        System.out.println("service2");
        service3();
    }

    private synchronized void service3() {
        System.out.println("service3");
    }
}

class SynLockInThread extends Thread{
    @Override
    public void run() {
        Services services = new Services();
        services.service1();
    }
}