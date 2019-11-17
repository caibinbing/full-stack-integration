package main.java.multithread.chapter3;

/*
    join()的作用是等待线程对象销毁
    当前线程无限期阻塞，等待线程对象销毁后再继续执行当前线程
    join与synchronized区别：
        join内部使用wait()方法进行等待
        synchronized使用“对象监视器”原理
 */

public class RunJoinPreTest {
    public static void main(String[] args) {
        try {

            JoinPreTestThread joinPreTestThread = new JoinPreTestThread();
            joinPreTestThread.start();
            //Thread.sleep(?)
            joinPreTestThread.join();
            System.out.println("get it");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class JoinPreTestThread extends Thread {
    @Override
    public void run() {
        try {
            int secondVal = (int) (Math.random() * 10000);
            System.out.println(secondVal);
            Thread.sleep(secondVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
