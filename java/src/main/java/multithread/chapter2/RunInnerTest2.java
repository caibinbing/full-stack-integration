package main.java.multithread.chapter2;

//同步代码块对innerclass3上锁后，其他线程只能以同步的方式调用innerclass3中的静态同步方法
public class RunInnerTest2 {
    public static void main(String[] args) {
        final OuterClass2.InnerClass2 innerClass2 = new OuterClass2.InnerClass2();
        final OuterClass2.InnerClass3 innerClass3 = new OuterClass2.InnerClass3();
        Thread thread1 = new Thread(() -> innerClass2.method1(innerClass3), "T1");
        Thread thread2 = new Thread(innerClass2::method2, "T2");
        Thread thread3 = new Thread(innerClass3::method1, "T3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class OuterClass2 {
    static class InnerClass2 {
        void method1(InnerClass3 innerClass3) {
            String threadName = Thread.currentThread().getName();
            synchronized (innerClass3) {
                System.out.println(threadName + "进入InnerClass2类中的method1方法");
                for (int i = 0; i < 10; i++) {
                    System.out.println("i = " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(threadName + "离开InnerClass2类中的method1方法");
            }
        }

        void method2() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "进入InnerClass2类中的method2方法");
            for (int j = 0; j < 10; j++) {
                System.out.println("j = " + j);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(threadName + "离开InnerClass2类中的method2方法");
        }
    }

    static class InnerClass3 {
        synchronized void method1() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "进入InnerClass3中的method1方法");
            for (int k = 0; k < 10; k++) {
                System.out.println("k = " + k);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(threadName + "离开InnerClass3中的method1方法");
        }
    }
}
