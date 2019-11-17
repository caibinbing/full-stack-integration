package main.java.multithread.chapter5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//任务执行完了，但进程还未销毁
public class RunTimer {
    //    private static Timer timer = new Timer();
    // 设置成守护线程，程序可以正常停止
    private static Timer timer = new Timer(true);

    static class FirstTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("运行了！时间为：" + LocalDate.now());
        }
    }

    public static void main(String[] args) {
        FirstTask firstTask = new FirstTask();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String now = formatter.format(localDateTime);
        System.out.println("字符串时间：" + now);
        timer.schedule(firstTask, new Date());
    }
}
