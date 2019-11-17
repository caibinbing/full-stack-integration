package main.java.multithread.chapter2;

import java.time.LocalTime;

public class RunSynBlockMoreObjThread {
    public static void main(String[] args) {
        SynBlockMoreObjService synBlockMoreObjService = new SynBlockMoreObjService();
        SynBlockMoreObjThreadA synBlockMoreObjThreadA = new SynBlockMoreObjThreadA(synBlockMoreObjService);
        synBlockMoreObjThreadA.setName("A");
        synBlockMoreObjThreadA.start();
        SynBlockMoreObjThreadB synBlockMoreObjThreadB = new SynBlockMoreObjThreadB(synBlockMoreObjService);
        synBlockMoreObjThreadB.setName("B");
        synBlockMoreObjThreadB.start();
    }
}

class SynBlockMoreObjService {
    static void printA() {
        synchronized (SynBlockMoreObjService.class) {
            try {
                System.out.println("Thread name = " + Thread.currentThread().getName()
                        + " at " + LocalTime.now() + " enter printA");
                Thread.sleep(1000);
                System.out.println("Thread name = " + Thread.currentThread().getName()
                        + " at " + LocalTime.now() + " leave printA");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void printB() {
        synchronized (SynBlockMoreObjService.class) {
            System.out.println("Thread name = " + Thread.currentThread().getName()
                    + " at " + LocalTime.now() + " enter printB");
            System.out.println("Thread name = " + Thread.currentThread().getName()
                    + " at " + LocalTime.now() + " leave printB");
        }
    }
}

class SynBlockMoreObjThreadA extends Thread {
    private SynBlockMoreObjService synBlockMoreObjService;

    SynBlockMoreObjThreadA(SynBlockMoreObjService synBlockMoreObjService) {
        this.synBlockMoreObjService = synBlockMoreObjService;
    }

    @Override
    public void run() {
        synBlockMoreObjService.printA();
    }
}


class SynBlockMoreObjThreadB extends Thread {
    private SynBlockMoreObjService synBlockMoreObjService;

    SynBlockMoreObjThreadB(SynBlockMoreObjService synBlockMoreObjService) {
        this.synBlockMoreObjService = synBlockMoreObjService;
    }

    @Override
    public void run() {
        synBlockMoreObjService.printB();
    }
}