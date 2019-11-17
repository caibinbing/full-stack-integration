package main.java.multithread.chapter2;

public class RunDiffMethodThread {
    public static void main(String[] args) {
        DiffMethod diffMethod = new DiffMethod();
        DiffMethodThreadA diffMethodThreadA = new DiffMethodThreadA(diffMethod);
        diffMethodThreadA.setName("Thread-a");
        DiffMethodThreadB diffMethodThreadB = new DiffMethodThreadB(diffMethod);
        diffMethodThreadB.setName("Thread-b");
        diffMethodThreadA.start();
        diffMethodThreadB.start();
    }
}

class DiffMethod {
    synchronized void MethodA() {
        try {
            System.out.println("begin methodA threadName = " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end methodA endTime = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
        如果MethodB添加了synchronized关键字，则AB顺序执行
        如果MethodB没有synchronized关键字
        1）A线程先持有锁，B线程可以以异步的方式调用
        2）A线程先持有锁，B线程如果调用synchronized类型的方法需要等待，即同步
        3）额外发现：异步时，结束时间相同！
     */
    void MethodB() {
        try {
            System.out.println("begin methodB threadName = " + Thread.currentThread().getName()
                    + " begin time = " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("end methodB endTime = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class DiffMethodThreadA extends Thread {
    private DiffMethod diffMethod;

    DiffMethodThreadA(DiffMethod diffMethod) {
        this.diffMethod = diffMethod;
    }

    @Override
    public void run() {
        super.run();
        diffMethod.MethodA();
    }
}

class DiffMethodThreadB extends Thread {
    private DiffMethod diffMethod;

    DiffMethodThreadB(DiffMethod diffMethod) {
        this.diffMethod = diffMethod;
    }

    @Override
    public void run() {
        super.run();
        diffMethod.MethodB();
    }
}