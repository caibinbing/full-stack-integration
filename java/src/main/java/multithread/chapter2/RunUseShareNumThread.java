package main.java.multithread.chapter2;

public class RunUseShareNumThread {

    public static void main(String[] args) {
        HasShareNum numRef = new HasShareNum();
        UseShareNumThreadA useShareNumThreadA = new UseShareNumThreadA(numRef);
        useShareNumThreadA.start();
        UseShareNumThreadB useShareNumThreadB = new UseShareNumThreadB(numRef);
        useShareNumThreadB.start();
    }
}

class HasShareNum {
    //多个线程同事访问一个没有同步的方法的实例变量。出现“非线程安全”问题
    private int num = 0;

    void addI(String username) {
        try {
            if ("a".equals(username)) {
                num = 100;
                System.out.println("a set over!");
                Thread.sleep(2000);
            } else {
                num = 200;
                System.out.println("b set over!");
            }
            System.out.println(username + " num = " + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class UseShareNumThreadA extends Thread {
    private HasShareNum numRef;

    UseShareNumThreadA(HasShareNum numRef) {
        super();
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("a");
    }
}

class UseShareNumThreadB extends Thread {
    private HasShareNum numRef;

    UseShareNumThreadB(HasShareNum numRef) {
        super();
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("b");
    }
}