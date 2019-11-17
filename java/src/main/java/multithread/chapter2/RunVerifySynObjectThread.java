package main.java.multithread.chapter2;

/*
    synchronized(this)代码块也是锁定当前对象
 */
public class RunVerifySynObjectThread {
    public static void main(String[] args) throws InterruptedException {
        VerifySynObjectTask verifySynObjectTask = new VerifySynObjectTask();
        VerifySynObjectThreadA verifySynObjectThreadA = new VerifySynObjectThreadA(verifySynObjectTask);
        verifySynObjectThreadA.start();
        Thread.sleep(100);
        VerifySynObjectThreadB verifySynObjectThreadB = new VerifySynObjectThreadB(verifySynObjectTask);
        verifySynObjectThreadB.start();
    }
}


class VerifySynObjectTask {
    void otherMethod() {
        System.out.println("------------run--otherMethod");
    }

    void doLongTimeTask() {
        synchronized (this) {
            for (int i = 0; i < 10000; i++) {
                System.out.println("synchronized threadName = " + Thread.currentThread().getName() + " i = " + (i + 1));
            }
        }
    }
}

class VerifySynObjectThreadA extends Thread {
    private VerifySynObjectTask verifySynObjectTask;

    VerifySynObjectThreadA(VerifySynObjectTask verifySynObjectTask) {
        this.verifySynObjectTask = verifySynObjectTask;
    }

    @Override
    public void run() {
        super.run();
        verifySynObjectTask.doLongTimeTask();
    }
}

class VerifySynObjectThreadB extends Thread {
    private VerifySynObjectTask verifySynObjectTask;

    VerifySynObjectThreadB(VerifySynObjectTask verifySynObjectTask) {
        this.verifySynObjectTask = verifySynObjectTask;
    }

    @Override
    public void run() {
        super.run();
        verifySynObjectTask.otherMethod();
    }
}
