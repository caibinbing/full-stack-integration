package main.java.multithread.chapter2;

/*
    不在synchronized块中就是异步执行，在synchronized块中就是同步执行
    非同步时交叉打印
    同步时排队打印
 */
public class RunHalfSynThread {
    public static void main(String[] args) {
        HalfSynTask halfSynTask = new HalfSynTask();
        HalfSynTaskThreadA halfSynTaskThreadA = new HalfSynTaskThreadA(halfSynTask);
        halfSynTaskThreadA.setName("a");
        halfSynTaskThreadA.start();
        HalfSynTaskThreadB halfSynTaskThreadB = new HalfSynTaskThreadB(halfSynTask);
        halfSynTaskThreadB.setName("b");
        halfSynTaskThreadB.start();
    }
}

class HalfSynTask {
    void doLonfgTimeTask() {
        for (int i = 0; i < 100; ++i) {
            System.out.println("not synchronized threadName = " + Thread.currentThread().getName() + " i = " + (i + 1));
        }
        System.out.println();
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println("synchronized threadName = " + Thread.currentThread().getName() + " i = " + (i + 1));
            }
        }
    }
}

class HalfSynTaskThreadA extends Thread {
    private HalfSynTask halfSynTask;

    HalfSynTaskThreadA(HalfSynTask halfSynTask) {
        this.halfSynTask = halfSynTask;
    }

    @Override
    public void run() {
        super.run();
        halfSynTask.doLonfgTimeTask();
    }
}

class HalfSynTaskThreadB extends Thread {
    private HalfSynTask halfSynTask;

    HalfSynTaskThreadB(HalfSynTask halfSynTask) {
        this.halfSynTask = halfSynTask;
    }

    @Override
    public void run() {
        super.run();
        halfSynTask.doLonfgTimeTask();
    }
}