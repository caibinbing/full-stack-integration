package main.java.multithread.chapter2;

public class RunStringAndSynThread {
    public static void main(String[] args) {
        StringAndSynService stringAndSynService = new StringAndSynService();
        StringAndSynThreadA stringAndSynThreadA = new StringAndSynThreadA(stringAndSynService);
        stringAndSynThreadA.setName("A");
        stringAndSynThreadA.start();
        StringAndSynThreadB stringAndSynThreadB = new StringAndSynThreadB(stringAndSynService);
        stringAndSynThreadB.setName("B");
        stringAndSynThreadB.start();
    }
}

class StringAndSynService {
    static void print(Object object) {
        try {
            //String常量池带来的问题：两个线程的String值都是AA，两个线程持有相同的锁
            //大多数情况下，同步synchronized代码块都不使用String做为锁对象改用其他，比如new Object()，它不放入缓存中
//            synchronized (stringParam) {
            synchronized (object) {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class StringAndSynThreadA extends Thread {
    private StringAndSynService stringAndSynService;

    StringAndSynThreadA(StringAndSynService stringAndSynService) {
        this.stringAndSynService = stringAndSynService;
    }

    @Override
    public void run() {
//        stringAndSynService.print("AA");
        stringAndSynService.print(new Object());
    }
}

class StringAndSynThreadB extends Thread {
    private StringAndSynService stringAndSynService;

    StringAndSynThreadB(StringAndSynService stringAndSynService) {
        this.stringAndSynService = stringAndSynService;
    }

    @Override
    public void run() {
//        stringAndSynService.print("AA");
        stringAndSynService.print(new Object());
    }
}