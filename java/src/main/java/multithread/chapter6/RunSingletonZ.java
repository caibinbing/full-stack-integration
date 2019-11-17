package main.java.multithread.chapter6;

public class RunSingletonZ {
    //hashCode是同一个值，说明对象同一个，实现了立即加载型单例设计模式
    public static void main(String[] args) {
        SingletonZThread t1 = new SingletonZThread();
        SingletonZThread t2 = new SingletonZThread();
        SingletonZThread t3 = new SingletonZThread();
        t1.start();
        t2.start();
        t3.start();
    }

}

class SingletonZ {
    //构造函数私有化
    private SingletonZ() {
    }

    private static RunSingletonZ singleton = new RunSingletonZ();

    //提供创建对象的方法
    static RunSingletonZ getInstance() {
        return singleton;
    }
}

class SingletonZThread extends Thread {
    @Override
    public void run() {
        System.out.println(SingletonZ.getInstance().hashCode());
    }
}