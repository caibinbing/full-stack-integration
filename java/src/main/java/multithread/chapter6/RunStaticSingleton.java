package main.java.multithread.chapter6;

/*
    静态代码块中的代码在使用类的时候就已经执行了
 */
public class RunStaticSingleton {
    public static void main(String[] args) {
        StaticObjThread t1 = new StaticObjThread();
        StaticObjThread t2 = new StaticObjThread();
        StaticObjThread t3 = new StaticObjThread();
        t1.start();
        t2.start();
        t3.start();
    }
}

class StaticObj {
    private StaticObj() {

    }

    private static StaticObj staticObj;

    static {
        staticObj = new StaticObj();
    }

    static StaticObj getInstance() {
        return staticObj;
    }
}

class StaticObjThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(StaticObj.getInstance().hashCode());
        }
    }
}