package main.java.multithread.chapter6;

/*
    解决懒汉式遇到多线程的问题
    DCL也是大多数多线程结合单例模式使用的解决方案
 */
public class RunDCL {
    public static void main(String[] args) {
        DCLThread t1 = new DCLThread();
        DCLThread t2 = new DCLThread();
        DCLThread t3 = new DCLThread();
        t1.start();
        t2.start();
        t3.start();
    }
}

class DCLObj {
    private DCLObj() {
    }

    private volatile static DCLObj dclObj;

    static DCLObj getInstance() {
        try {
            if (dclObj == null) {
                Thread.sleep(3000);
                synchronized (DCLObj.class) {
                    if (dclObj == null) {
                        dclObj = new DCLObj();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return dclObj;
    }
}

class DCLThread extends Thread {
    @Override
    public void run() {
        System.out.println(DCLObj.getInstance().hashCode());
    }
}