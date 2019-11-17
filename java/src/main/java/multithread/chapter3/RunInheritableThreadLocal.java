package main.java.multithread.chapter3;


import java.time.LocalDate;
import java.time.LocalTime;

/*
    注意：如果子线程在取得值的同事，主线程更改InheritableThreadLocal中的值，子线程取到的还是旧值
 */
public class RunInheritableThreadLocal {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 2; i++) {
                System.out.println("在Main线程中取值 = " + InheritableThreadLocalTools.inheritableThreadLocalExt.get());
                Thread.sleep(100);
            }
            Thread.sleep(1000);
            InheritableThreadLocalThreadA inheritableThreadLocalThreadA = new InheritableThreadLocalThreadA();
            inheritableThreadLocalThreadA.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class InheritableThreadLocalExt extends InheritableThreadLocal {
    @Override
    protected Object initialValue() {
        return LocalDate.now();
    }
    //在继承的同时还可以对值进行进一步的处理
    /*
        LocalTime.MIDNIGHT -> 00:00
        LocalTime.NOON -> 12:00
     */
    @Override
    protected Object childValue(Object parentValue) {
        return parentValue +" " + LocalTime.now();
    }
}

class InheritableThreadLocalTools {
    static InheritableThreadLocalExt inheritableThreadLocalExt = new InheritableThreadLocalExt();
}

class InheritableThreadLocalThreadA extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 2; i++) {
                System.out.println("在A线程中取值 = " + InheritableThreadLocalTools.inheritableThreadLocalExt.get());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
