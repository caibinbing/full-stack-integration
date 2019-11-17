package main.java.multithread.chapter2;

public class RunMethodLockObjectThread {
    public static void main(String[] args) {
        MethodLockObject methodLockObject = new MethodLockObject();
        MethodLockObjectThreadA methodLockObjectThreadA = new MethodLockObjectThreadA(methodLockObject);
        methodLockObjectThreadA.setName("Thread-a");
        methodLockObjectThreadA.start();
        MethodLockObjectThreadB methodLockObjectThreadB = new MethodLockObjectThreadB(methodLockObject);
        methodLockObjectThreadB.setName("Thread-b");
        methodLockObjectThreadB.start();
    }
}

class MethodLockObject {
    //synchronized方法一定是排队运行的。可以删除synchronized关键字再运行比较。
    //只有共享资源的读写访问才需要同步化。
    synchronized void method() {
        try {
            System.out.println("begin method threadName = " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end method threadName = " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MethodLockObjectThreadA extends Thread {
    private MethodLockObject methodLockObject;

    MethodLockObjectThreadA(MethodLockObject methodLockObject) {
        super();
        this.methodLockObject = methodLockObject;
    }
    @Override
    public void run() {
        super.run();
        methodLockObject.method();
    }
}

class MethodLockObjectThreadB extends Thread {
    private MethodLockObject methodLockObject;

    MethodLockObjectThreadB(MethodLockObject methodLockObject) {
        super();
        this.methodLockObject = methodLockObject;
    }
    @Override
    public void run() {
        super.run();
        methodLockObject.method();
    }
}