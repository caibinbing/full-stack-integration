package main.java.multithread.chapter5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//任务被延时但还是一个个顺序运行
//此例5秒后立即执行task任务
public class RunDelayTask {
    static class DelayTask extends TimerTask {
        @Override
        public void run() {
            try {
                System.out.println("A run time : " + LocalTime.now());
                Thread.sleep(5000);
                System.out.println("A end time : " + LocalTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            DelayTask task = new DelayTask();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sDate = "2019-08-19 16:22:55";
            Timer timer = new Timer();
            Date date = sdf.parse(sDate);
            System.out.println("String time : " + date.toString()
                    + " 当前时间：" + LocalTime.now());
            timer.schedule(task, date, 4000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
