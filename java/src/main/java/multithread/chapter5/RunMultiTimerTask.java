package main.java.multithread.chapter5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//TimerTask以队列的方式一个个被顺序执行，执行时间有可能和预期的时间不一致
public class RunMultiTimerTask {
    private static Timer timer = new Timer();

    static class MultiTaskA extends TimerTask {
        @Override
        public void run() {
            System.out.println("运行了！时间为：" + new Date());
        }
    }

    static class MultiTaskB extends TimerTask {
        @Override
        public void run() {
            System.out.println("run 2 time: " + new Date());
        }
    }

    public static void main(String[] args) {
        try {
            MultiTaskA multiTaskA = new MultiTaskA();
            MultiTaskB multiTaskB = new MultiTaskB();
            SimpleDateFormat sdfA = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdfB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sDateA = "2019-08-19 14:57:50";
            String sDateB = "2019-08-19 14:58:00";
            Date dateA = sdfA.parse(sDateA);
            Date dateB = sdfB.parse(sDateB);
            System.out.println("字符串A时间：" + dateA.toLocaleString()
                    + " 当前时间：" + LocalTime.now());
            System.out.println("字符串B时间：" + dateB.toLocaleString()
                    + " 当前时间：" + LocalTime.now());
            timer.schedule(multiTaskA, dateA);
            timer.schedule(multiTaskB, dateB);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

