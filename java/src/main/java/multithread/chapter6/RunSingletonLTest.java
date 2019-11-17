package main.java.multithread.chapter6;

public class RunSingletonLTest {

    public static void main(String[] args) {
        //会出现3个不同的hash值
        TestThread t1 = new TestThread();
        TestThread t2 = new TestThread();
        TestThread t3 = new TestThread();
        t1.start();
        t2.start();
        t3.start();
    }
}

class TestObj {
    private TestObj() {
    }

    private static TestObj testObj;

    static TestObj getInstance() {
        try {
            if (testObj == null) {
                //模拟在创建对象之前做一些准备性的工作
                Thread.sleep(3000);
                testObj = new TestObj();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return testObj;
    }
}

class TestThread extends Thread {
    @Override
    public void run() {
        System.out.println(TestObj.getInstance().hashCode());
    }
}