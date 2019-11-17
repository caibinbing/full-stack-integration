package main.java.multithread.chapter5;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

/*
    schedule(TimerTask task, long delay, long period)
    以当前时间为参考，延迟指定毫秒，间隔period无限循环执行task任务。
 */
public class RunPeriodSchedule {
    static class PeriodTimerTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("run time : " + LocalTime.now());
        }
    }

    public static void main(String[] args) {
        PeriodTimerTask task = new PeriodTimerTask();
        Timer timer = new Timer();
        System.out.println("current time : " + LocalTime.now());
        timer.schedule(task, 3000, 5000);
    }
}
