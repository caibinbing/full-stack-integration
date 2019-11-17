package main.java.multithread.chapter2;

public class RunVolatileTestThread {
    public static void main(String[] args) {
        VolatileTestThread[] threads = new VolatileTestThread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new VolatileTestThread();
        }
        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }
    }
}

class VolatileTestThread extends Thread {
    volatile static int count;
    /*
        注意一定要添加static关键字
        这样synchronized与static锁的内容就是VolatileTestThread类
     */
    synchronized private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("count = " + count);
    }

    @Override
    public void run() {
        addCount();
    }
}
