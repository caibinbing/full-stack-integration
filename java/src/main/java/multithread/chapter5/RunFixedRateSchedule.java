package main.java.multithread.chapter5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/*
    schedule和scheduleAtFixedRate都会按顺序执行
    所以不用考虑“非线程安全”的情况
    区别：
        schedule：没被延时，下次任务执行时间参考上次任务“开始”时间
        scheduleAtFixedRate:没被延时，下次任务执行时间参考上次任务“结束”时间
        延时情况：二者都以上次结束时间开始
        schedule不具备追赶执行，scheduleAtFixedRate具备追赶执行
 */
public class RunFixedRateSchedule {
    private static Timer timer = new Timer();
    private static int runCount = 0;

    static class FixedRateTask extends TimerTask {
        @Override
        public void run() {
            try {
                System.out.println("1 begin run time : " + LocalTime.now());
                Thread.sleep(2000);
                System.out.println("1 end run time : " + LocalTime.now());
                if (runCount == 5) {
                    timer.cancel();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            FixedRateTask task = new FixedRateTask();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sDate = "2019-08-20 10:19:20";
            Date date = sdf.parse(sDate);
            //以上次开始时间+3秒再执行
//            timer.schedule(task, date, 3000);
            //以上次结束时间直接执行
            timer.scheduleAtFixedRate(task, date, 3000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
