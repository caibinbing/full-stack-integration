package main.java.multithread.chapter1;

public class RunLockStopThread {
    public static void main(String[] args) {
        try{
            LockStopThread lockStopThread = new LockStopThread();
            lockStopThread.start();
            Thread.sleep(1000);
            /**
             * This method has been deprecated, as it is
             * inherently deadlock-prone.  If the target thread holds a lock on the
             * monitor protecting a critical system resource when it is suspended, no
             * thread can access this resource until the target thread is resumed. If
             * the thread that would resume the target thread attempts to lock this
             * monitor prior to calling <code>resume</code>, deadlock results.  Such
             * deadlocks typically manifest themselves as "frozen" processes.
             */
            lockStopThread.suspend();
            System.out.println("main end!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class LockStopThread extends Thread {
    private long i = 0;

    @Override
    public void run() {
        while (true) {
            i++;
            //添加println后将不打印main end!
            //原因：当程序运行到println()方法内部停止时，同步锁未被释放。
            //System.out.println(i);
        }
    }
}