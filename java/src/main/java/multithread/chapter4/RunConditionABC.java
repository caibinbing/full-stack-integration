package main.java.multithread.chapter4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//使用Condition对象可以对线程执行的业务进行排序规划
//利用指定的condition来唤醒
public class RunConditionABC {
    private volatile static int nextPrintWho = 1;
    private static ReentrantLock lock = new ReentrantLock();
    private final static Condition conditinoA = lock.newCondition();
    private final static Condition conditinoB = lock.newCondition();
    private final static Condition conditinoC = lock.newCondition();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 1) {
                    conditinoA.await();
                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("ThreadA " + (i + 1));
                }
                nextPrintWho = 2;
                conditinoB.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 2) {
                    conditinoB.await();
                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("ThreadB " + (i + 1));
                }
                nextPrintWho = 3;
                conditinoC.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread threadC = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 3) {
                    conditinoC.await();
                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("ThreadC " + (i + 1));
                }
                nextPrintWho = 1;
                conditinoA.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread[] aArray = new Thread[5];
        Thread[] bArray = new Thread[5];
        Thread[] cArray = new Thread[5];
        for (int i = 0; i < 5; i++) {
            aArray[i] = new Thread(threadA);
            bArray[i] = new Thread(threadB);
            cArray[i] = new Thread(threadC);
            aArray[i].start();
            bArray[i].start();
            cArray[i].start();

        }
    }
}
