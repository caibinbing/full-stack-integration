package main.java.multithread.chapter2;

//在内置类中有两种同步方法，但使用的却是不同的锁，打印结果也是异步的
public class RunInnerTest1 {
    public static void main(String[] args) {
        final OuterClass1.Inner1 inner1 = new OuterClass1.Inner1();
        Thread thread1 = new Thread(inner1::method1, "A");
        Thread thread2 = new Thread(inner1::method2, "B");
        thread1.start();
        thread2.start();
    }
}

class OuterClass1 {
    static class Inner1 {
        void method1() {
            synchronized ("其他的锁") {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " i = " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        synchronized void method2() {
            for (int i = 11; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + " i = " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}