package main.java.multithread.chapter6;

/*
    静态内之类实现单例模式
    静态内置类可以达到线程安全问题
    但如果遇到序列化对象时，使用默认的方式运行得到的结果还是多例的
 */
public class RunInnerClassSingleton {
    public static void main(String[] args) {
        ObjThread t1 = new ObjThread();
        ObjThread t2 = new ObjThread();
        ObjThread t3 = new ObjThread();
        t1.start();
        t2.start();
        t3.start();
    }
}

class Obj {
    private Obj() {
    }

    private static class ObjHandler {
        private static Obj obj = new Obj();
    }

    static Obj getInstance() {
        return ObjHandler.obj;
    }
}

class ObjThread extends Thread {
    @Override
    public void run() {
        System.out.println(Obj.getInstance().hashCode());
    }
}