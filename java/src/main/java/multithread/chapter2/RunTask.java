package main.java.multithread.chapter2;

public class RunTask {
    public static void main(String[] args) {
//        Task task = new Task();
        FasterTask task = new FasterTask();
        TaskThread1 taskThread1 = new TaskThread1(task);
        taskThread1.start();
        TaskThread2 taskThread2 = new TaskThread2(task);
        taskThread2.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long beginTime = CommonUtils.beginTime1;
        if (CommonUtils.beginTime2 < CommonUtils.beginTime1) {
            beginTime = CommonUtils.beginTime2;
        }
        long endTime = CommonUtils.endTime1;
        if (CommonUtils.endTime2 > CommonUtils.endTime1) {
            endTime = CommonUtils.endTime2;
        }
        System.out.println("use time : " + ((endTime - beginTime) / 1000));
    }
}

class Task {
    private String getData1;
    private String getData2;

    synchronized void doLongTimeTask() {
        try {
            System.out.println("begin task");
            Thread.sleep(3000);
            getData1 = "长时间处理任务后从远程返回的值1 threadName = "
                    + Thread.currentThread().getName();
            getData2 = "长时间处理任务从远程返回的值2 threadName = "
                    + Thread.currentThread().getName();
            System.out.println(getData1);
            System.out.println(getData2);
            System.out.println("end task");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
    使用同步代码块解决同步方法的弊端：执行效率
 */
class FasterTask extends Task{
    private String getData1;
    private String getData2;

    void doLongTimeTask() {
        try {
            System.out.println("begin task");
            Thread.sleep(3000);
            synchronized (this) {
                getData1 = "长时间处理任务后从远程返回的值1 threadName = "
                        + Thread.currentThread().getName();
                getData2 = "长时间处理任务从远程返回的值2 threadName = "
                        + Thread.currentThread().getName();
            }
            System.out.println(getData1);
            System.out.println(getData2);
            System.out.println("end task");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CommonUtils {
    static long beginTime1;
    static long endTime1;
    static long beginTime2;
    static long endTime2;
}

class TaskThread1 extends Thread {
    private Task task;

    TaskThread1(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        CommonUtils.beginTime1 = System.currentTimeMillis();
        task.doLongTimeTask();
        CommonUtils.endTime1 = System.currentTimeMillis();
    }
}

class TaskThread2 extends Thread {
    private Task task;

    TaskThread2(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        CommonUtils.beginTime2 = System.currentTimeMillis();
        task.doLongTimeTask();
        CommonUtils.endTime2 = System.currentTimeMillis();
    }
}