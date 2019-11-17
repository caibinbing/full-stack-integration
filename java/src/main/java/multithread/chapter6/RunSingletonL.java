package main.java.multithread.chapter6;

/*
    这个单例模式代码是错的。
    不能保证单例的状态。
 */
public class RunSingletonL {
    public static void main(String[] args) {
        SingletonLThread t1 = new SingletonLThread();
        SingletonLThread t2 = new SingletonLThread();
        SingletonLThread t3 = new SingletonLThread();
        t1.start();
        t2.start();
        t3.start();
    }
}

class SingletonLThread extends Thread {
    @Override
    public void run() {
        System.out.println(SingletonL.getInstance().hashCode());
    }
}

class SingletonL {
    //构造函数私有化
    private SingletonL() {
    }

    private static RunSingletonL runSingletonL;

    //提供创建对象的方法
    static RunSingletonL getInstance() {
        if (runSingletonL == null) {
            runSingletonL = new RunSingletonL();
        }
        return runSingletonL;
    }
}