package main.java.multithread.chapter1;

import java.util.Random;

public class RunTimeTestPriorityThread {
    public static void main(String[] args) {
        for (int i = 0; i < 5; ++i) {
            TimeTestPriorityThread1 thread1 = new TimeTestPriorityThread1();
            //高优先级不代表全部优先执行完！
            thread1.setPriority(1);
            thread1.start();
            TimeTestPriorityThread2 thread2 = new TimeTestPriorityThread2();
            thread2.setPriority(10);
            thread2.start();
        }
    }
}

class TimeTestPriorityThread1 extends Thread{
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        long addResult = 0;
        for (int j = 0; j < 10; ++j) {
            for (int i = 0; i < 50000; ++i) {
                Random random = new Random();
                random.nextInt();
                addResult = addResult + i;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time Test Priority Thread1 use time = " +
                (endTime - beginTime));
    }
}

class TimeTestPriorityThread2 extends Thread{
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        long addResult = 0;
        for (int j = 0; j < 10; ++j) {
            for (int i = 0; i < 50000; ++i) {
                Random random = new Random();
                random.nextInt();
                addResult = addResult + i;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time Test Priority Thread2 use time = " +
                (endTime - beginTime));
    }
}