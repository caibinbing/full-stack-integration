package main.java.multithread.chapter2;

/*
    当一个线程执行的代码出现异常时，其所持有的锁会被自动释放
 */
public class RunThrowExceptionNoLockThread {
    public static void main(String[] args) {
        try{
            ThrowExceptionNoLockService throwExceptionNoLockService = new ThrowExceptionNoLockService();
            ThrowExceptionNoLockThreadA throwExceptionNoLockThreadA = new ThrowExceptionNoLockThreadA(throwExceptionNoLockService);
            throwExceptionNoLockThreadA.setName("a");
            throwExceptionNoLockThreadA.start();
            Thread.sleep(500);
            //出现异常的锁会被自动释放。之后b线程开始打印
            ThrowExceptionNoLockThreadB throwExceptionNoLockThreadB = new ThrowExceptionNoLockThreadB(throwExceptionNoLockService);
            throwExceptionNoLockThreadB.setName("b");
            throwExceptionNoLockThreadB.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThrowExceptionNoLockService {
    synchronized void testMethod() {
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("ThreadName = " + Thread.currentThread().getName()
                    + " run beginTime = " + System.currentTimeMillis());
            int i = 1;
            while (i == 1) {
                if (("" + Math.random()).substring(0, 8).equals("0.123456")) {
                    System.out.println("ThreadName = "
                            + Thread.currentThread().getName()
                            + " run exceptionTime = "
                            + System.currentTimeMillis());
                    Integer.parseInt("a");
                }
            }
        } else {
            System.out.println("Thread B run Time = " + System.currentTimeMillis());

        }
    }
}

class ThrowExceptionNoLockThreadA extends Thread {
    private ThrowExceptionNoLockService throwExceptionNoLockService;

    ThrowExceptionNoLockThreadA(ThrowExceptionNoLockService throwExceptionNoLockService) {
        super();
        this.throwExceptionNoLockService = throwExceptionNoLockService;
    }

    @Override
    public void run() {
        throwExceptionNoLockService.testMethod();
    }
}

class ThrowExceptionNoLockThreadB extends Thread {
    private ThrowExceptionNoLockService throwExceptionNoLockService;

    ThrowExceptionNoLockThreadB(ThrowExceptionNoLockService throwExceptionNoLockService) {
        super();
        this.throwExceptionNoLockService = throwExceptionNoLockService;
    }

    @Override
    public void run() {
        throwExceptionNoLockService.testMethod();
    }
}