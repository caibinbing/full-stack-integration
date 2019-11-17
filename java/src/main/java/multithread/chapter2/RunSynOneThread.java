package main.java.multithread.chapter2;

public class RunSynOneThread {
    public static void main(String[] args) {
        RunSynOneService runSynOneService = new RunSynOneService();
        SynOneThreadA synOneThreadA = new SynOneThreadA(runSynOneService);
        synOneThreadA.setName("a");
        synOneThreadA.start();
        SynOneThreadB synOneThreadB = new SynOneThreadB(runSynOneService);
        synOneThreadB.setName("b");
        synOneThreadB.start();
    }
}

class RunSynOneService {
    void serviceMethod() {
        try {
            synchronized (this) {
                System.out.println("begin time = " + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("end tim = " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SynOneThreadA extends Thread {
    private RunSynOneService runSynOneService;

    SynOneThreadA(RunSynOneService runSynOneService) {
        this.runSynOneService = runSynOneService;
    }

    @Override
    public void run() {
        super.run();
        runSynOneService.serviceMethod();
    }
}

class SynOneThreadB extends Thread {
    private RunSynOneService runSynOneService;

    SynOneThreadB(RunSynOneService runSynOneService) {
        this.runSynOneService = runSynOneService;
    }

    @Override
    public void run() {
        super.run();
        runSynOneService.serviceMethod();
    }
}