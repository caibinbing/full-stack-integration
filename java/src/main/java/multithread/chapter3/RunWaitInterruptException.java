package main.java.multithread.chapter3;

//当线程呈wait()状态时，调用线程对象的interrupt()方法会出现Interrupted异常。
public class RunWaitInterruptException {
    public static void main(String[] args) {
        try{
            Object lock = new Object();
            WaitInterruptThreadA waitInterruptThreadA = new WaitInterruptThreadA(lock);
            waitInterruptThreadA.start();
            Thread.sleep(5000);
            waitInterruptThreadA.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WaitInterruptService {
    void WaitInterruptMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("begin wait()");
                lock.wait();
                System.out.println("end wait()");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WaitInterruptThreadA extends Thread {
    private Object lock;

    WaitInterruptThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        WaitInterruptService waitInterruptService = new WaitInterruptService();
        waitInterruptService.WaitInterruptMethod(lock);
    }
}