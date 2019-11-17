package main.java.multithread.chapter5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/*
    schedule(TimerTask task, Date firstTime, long period)
    指定日期之后，指定间隔，周期性无限循环，指定任务
    如果计划时间早于当前时间，则立即执行task任务
 */
public class RunPeriodTask {
    static class PeriodTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("运行了！时间为：" + LocalDate.now() + " " + LocalTime.now());
        }
    }

    public static void main(String[] args) {
        try {
            PeriodTask task = new PeriodTask();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sDate = "2019-08-19 16:05:34";
            Timer timer = new Timer();
            Date date = sdf.parse(sDate);
            System.out.println("字符串时间：" + date.toLocaleString()
                    + " 当前时间：" + LocalTime.now());
            timer.schedule(task, date, 4000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
