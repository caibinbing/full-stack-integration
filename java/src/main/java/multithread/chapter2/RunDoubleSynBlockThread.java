package main.java.multithread.chapter2;

import java.time.LocalTime;

/*
    当一个线程访问object的一个synchronized(this)同步代码块时
    其他线程对同一个object中所有其他的synchronized(this)同步代码块的访问将被阻塞
    这说明synchronized使用的“对象监视器”是同一个。
    两个同步代码块按顺序执行
 */
public class RunDoubleSynBlockThread {
    public static void main(String[] args) {
        DoubleSynBlockService doubleSynBlockService = new DoubleSynBlockService();
        DoubleSynBlockThreadA doubleSynBlockThreadA = new DoubleSynBlockThreadA((doubleSynBlockService));
        doubleSynBlockThreadA.setName("a");
        doubleSynBlockThreadA.start();
        DoubleSynBlockThreadB doubleSynBlockThreadB = new DoubleSynBlockThreadB(doubleSynBlockService);
        doubleSynBlockThreadB.setName("b");
        doubleSynBlockThreadB.start();
    }
}

class DoubleSynBlockService {
    void DoubleSynBlockServiceMethodA() {
        try {
            synchronized (this) {
                System.out.println("A begin time = " + LocalTime.now());
                Thread.sleep(2000);
                System.out.println("A end time = " + LocalTime.now());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void DoubleSynBlockServiceMethodB() {
        synchronized (this) {
            System.out.println("B begin time = " + LocalTime.now());
            System.out.println("B end time = " + LocalTime.now());
        }
    }
}

class DoubleSynBlockThreadA extends Thread {
    private DoubleSynBlockService doubleSynBlockService;

    DoubleSynBlockThreadA(DoubleSynBlockService doubleSynBlockService) {
        this.doubleSynBlockService = doubleSynBlockService;
    }

    @Override
    public void run() {
        super.run();
        doubleSynBlockService.DoubleSynBlockServiceMethodA();
    }
}

class DoubleSynBlockThreadB extends Thread {
    private DoubleSynBlockService doubleSynBlockService;

    DoubleSynBlockThreadB(DoubleSynBlockService doubleSynBlockService) {
        this.doubleSynBlockService = doubleSynBlockService;
    }

    @Override
    public void run() {
        super.run();
        doubleSynBlockService.DoubleSynBlockServiceMethodB();
    }
}

