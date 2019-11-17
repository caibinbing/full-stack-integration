package main.java.multithread.chapter2;

//局部变量不存在非线程安全问题
public class RunHasSelfPrivateNumThread {
    public static void main(String[] args) {
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        Thread threadA = new HasSelfPrivateNumThreadA(hasSelfPrivateNum);
        threadA.start();
        Thread threadB = new HasSelfPrivateNumThreadB(hasSelfPrivateNum);
        threadB.start();
    }
}

class HasSelfPrivateNum {
    void addI(String username) {
        try {
            int num = 0;
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

class HasSelfPrivateNumThreadA extends Thread {
    private HasSelfPrivateNum hasSelfPrivateNum;

    HasSelfPrivateNumThreadA(HasSelfPrivateNum hasSelfPrivateNum) {
        super();
        this.hasSelfPrivateNum = hasSelfPrivateNum;
    }

    @Override
    public void run() {
        super.run();
        hasSelfPrivateNum.addI("a");
    }
}

class HasSelfPrivateNumThreadB extends Thread {
    private HasSelfPrivateNum hasSelfPrivateNum;

    HasSelfPrivateNumThreadB(HasSelfPrivateNum hasSelfPrivateNum) {
        super();
        this.hasSelfPrivateNum = hasSelfPrivateNum;
    }

    @Override
    public void run() {
        super.run();
        hasSelfPrivateNum.addI("b");
    }
}