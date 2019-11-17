package main.java.multithread.chapter5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/*
    cancel()方法是将自身从任务队列中移除，其他任务不受影响
    注意：有时并不一定会停止执行计划任务，而是正常执行。
    因为：cancel方法有时并没有争抢到queue锁，所以TimerTask类中的任务继续正常执行
 */
public class RunCancel {
    static class CancelTaskA extends TimerTask {
        @Override
        public void run() {
            System.out.println("A运行了，时间为：" + LocalTime.now());
            this.cancel();
            //cancel后的代码依然会执行
            System.out.println("测试，A已经被删除，还执行吗？");
        }
    }

    static class CancelTaskB extends TimerTask {
        @Override
        public void run() {
            System.out.println("B run time : " + LocalTime.now());
        }
    }

    public static void main(String[] args) {
        try {
            CancelTaskA taskA = new CancelTaskA();
            CancelTaskB taskB = new CancelTaskB();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timer timer = new Timer();
            String sDate = "2019-08-19 16:31:27";
            Date date = sdf.parse(sDate);
            System.out.println("string time : " + date.toLocaleString()
                    + " current time : " + LocalTime.now());
            timer.schedule(taskA, date, 4000);
            timer.schedule(taskB, date, 4000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
