package main.java.multithread.chapter5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class RunTimerTaskLater {
    private static Timer timer = new Timer();

    static class LaterTimerTaskA extends TimerTask {
        @Override
        public void run() {
            try {
                System.out.println("A begin time : " + LocalTime.now());
                Thread.sleep(20000);
                System.out.println("A end time : " + LocalTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class LaterTimerTaskB extends TimerTask {
        @Override
        public void run() {
            System.out.println("B begin time : " + LocalTime.now());
            System.out.println("run time : " + LocalTime.now());
            System.out.println("B end time : " + LocalTime.now());
        }
    }

    public static void main(String[] args) {
        try {
            LaterTimerTaskA taskA = new LaterTimerTaskA();
            LaterTimerTaskB taskB = new LaterTimerTaskB();
            SimpleDateFormat sdfA = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdfB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sDateA = "2019-08-19 15:20:00";
            String sDateB = "2019-08-19 15:21:00";
            Date dateA = sdfA.parse(sDateA);
            Date dateB = sdfB.parse(sDateB);
            System.out.println("A time : " + dateA.toLocaleString()
                    + " 当前时间：" + LocalTime.now());
            System.out.println("B time : " + dateB.toString()
                    + " 当前时间：" + LocalTime.now());
            //schedule(TimerTask task, Date time)：在time时刻执行task任务
            timer.schedule(taskA, dateA);
            timer.schedule(taskB, dateB);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
