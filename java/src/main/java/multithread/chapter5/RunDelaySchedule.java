package main.java.multithread.chapter5;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

/*
    schedule(TimerTask task, long delay)：
    以当前时间为参考，延迟指定毫秒数后执行一次TimerTask任务
 */
public class RunDelaySchedule {
    static class DelayTimerTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("run time : " + LocalTime.now());
        }
    }

    public static void main(String[] args) {
        DelayTimerTask task = new DelayTimerTask();
        Timer timer = new Timer();
        System.out.println("current time : " + LocalTime.now());
        timer.schedule(task, 7000);
    }
}
