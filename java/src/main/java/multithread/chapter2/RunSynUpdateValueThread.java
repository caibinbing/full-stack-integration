package main.java.multithread.chapter2;

public class RunSynUpdateValueThread {
    public static void main(String[] args) {
        try {

            SynUpdateValueService synUpdateValueService = new SynUpdateValueService();
            SynUpdateValueThreadA synUpdateValueThreadA = new SynUpdateValueThreadA(synUpdateValueService);
            synUpdateValueThreadA.start();
            Thread.sleep(1000);
            SynUpdateValueThreadB synUpdateValueThreadB = new SynUpdateValueThreadB(synUpdateValueService);
            synUpdateValueThreadB.start();
            System.out.println("已经发起停止命令了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SynUpdateValueService {
    private boolean isContinueRun = true;

    void runMethod() {
        while (isContinueRun) {
            //证明：关键字synchronized可以具有可视性。可以停下来。
            synchronized (new String()) {

            }
        }
        System.out.println("停下来了");
    }

    void stopMethod() {
        isContinueRun = false;
    }
}

class SynUpdateValueThreadA extends Thread {
    private SynUpdateValueService synUpdateValueService;

    SynUpdateValueThreadA(SynUpdateValueService synUpdateValueService) {
        this.synUpdateValueService = synUpdateValueService;
    }

    @Override
    public void run() {
        synUpdateValueService.runMethod();
    }
}


class SynUpdateValueThreadB extends Thread {
    private SynUpdateValueService synUpdateValueService;

    SynUpdateValueThreadB(SynUpdateValueService synUpdateValueService) {
        this.synUpdateValueService = synUpdateValueService;
    }

    @Override
    public void run() {
        synUpdateValueService.stopMethod();
    }
}