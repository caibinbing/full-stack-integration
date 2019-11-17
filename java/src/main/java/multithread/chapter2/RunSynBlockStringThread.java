package main.java.multithread.chapter2;

import java.time.LocalTime;

public class RunSynBlockStringThread {
    public static void main(String[] args) {
        SynBlockStringService synBlockStringService = new SynBlockStringService();
        SynBlockStringThreadA synBlockStringThreadA = new SynBlockStringThreadA(synBlockStringService);
        synBlockStringThreadA.setName("A");
        synBlockStringThreadA.start();
        SynBlockStringThreadB synBlockStringThreadB = new SynBlockStringThreadB(synBlockStringService);
        synBlockStringThreadB.setName("B");
        synBlockStringThreadB.start();
    }
}

class SynBlockStringService {
    private String usernameParam;
    private String passwordParam;
//    private String anyString = new String();

    void setUsernamePassword(String username, String password) {
        try {
            /*
                如果在一个类中有很多个synchronized方法，这时虽然能实现同步，但会阻塞。
                如果使用同步代码块锁fei非this对象，则是异步的，不与其他this同步方法争抢this锁
                对象监视器必须是同一个对象
             */
            String anyString = new String();
            synchronized (anyString) {
                System.out.println("thread name : " + Thread.currentThread().getName()
                        + " at " + LocalTime.now() + " entry syn block");
                usernameParam = username;
                Thread.sleep(3000);
                passwordParam = password;
                System.out.println("thread name : " + Thread.currentThread().getName()
                        + " at " + LocalTime.now() + " leave syn block");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SynBlockStringThreadA extends Thread {
    private SynBlockStringService synBlockStringService;

    SynBlockStringThreadA(SynBlockStringService synBlockStringService) {
        this.synBlockStringService = synBlockStringService;
    }

    @Override
    public void run() {
        synBlockStringService.setUsernamePassword("a", "aa");
    }
}

class SynBlockStringThreadB extends Thread {
    private SynBlockStringService synBlockStringService;

    SynBlockStringThreadB(SynBlockStringService synBlockStringService) {
        this.synBlockStringService = synBlockStringService;
    }

    @Override
    public void run() {
        synBlockStringService.setUsernamePassword("b", "bb");
    }
}