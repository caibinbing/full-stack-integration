package main.java.multithread.chapter3;

/*
    方法join()与interrupt()如果彼此遇到，则会出现InterruptedException。
    但进程按钮呈红色，因为A线程还在运行。
 */
public class RunJoinExceptionThread {
    public static void main(String[] args) {
        try {
            JoinExceptionThreadB joinExceptionThreadB = new JoinExceptionThreadB();
            joinExceptionThreadB.start();
            Thread.sleep(500);
            JoinExceptionThreadC joinExceptionThreadC = new JoinExceptionThreadC(joinExceptionThreadB);
            joinExceptionThreadC.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class JoinExceptionThreadA extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; ++i) {
            String s = new String();
            Math.random();
        }
    }
}

class JoinExceptionThreadB extends Thread {
    @Override
    public void run() {
        try {
            JoinExceptionThreadA joinExceptionThreadA = new JoinExceptionThreadA();
            joinExceptionThreadA.start();
            joinExceptionThreadA.join();
            System.out.println("线程B在run end处打印");
        } catch (InterruptedException e) {
            System.out.println("线程B在catch处打印");
            e.printStackTrace();
        }
    }
}

class JoinExceptionThreadC extends Thread {
    private JoinExceptionThreadB joinExceptionThreadB;

    JoinExceptionThreadC(JoinExceptionThreadB joinExceptionThreadB) {
        this.joinExceptionThreadB = joinExceptionThreadB;
    }

    @Override
    public void run() {
        joinExceptionThreadB.interrupt();
    }
}