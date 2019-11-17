package main.java.multithread.chapter3;

import java.time.LocalTime;

public class RunJoinTimeThread {
    public static void main(String[] args) {
        try {
            JoinTimeThread joinTimeThread = new JoinTimeThread();
            joinTimeThread.start();
            /*
                join等JoinTimeThread执行完，才执行main线程
                括号内的数值会覆盖原run方法中的sleep时间
                join与sleep的区别：
                join底层是wait，会释放锁，其他线程就可以调用此线程中的同步方法了。
                sleep不释放锁。
             */
            joinTimeThread.join(2000);
            //Thread.sleep(2000)
            System.out.println("end Timer = " + LocalTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class JoinTimeThread extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("begin Timer = " + LocalTime.now());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
