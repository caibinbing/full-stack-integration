package main.java.multithread.chapter1;

public class RunCountPriorityThread {
    public static void main(String[] args) {
        try {
            //优先级高的运行得快
            CountPriorityThreadA a = new CountPriorityThreadA();
            a.setPriority(Thread.NORM_PRIORITY - 3);
            a.start();
            CountPriorityThreadB b = new CountPriorityThreadB();
            b.setPriority(Thread.NORM_PRIORITY + 3);
            b.start();
            Thread.sleep(20000);
            a.stop();
            b.stop();
            System.out.println("a = " + a.getCount());
            System.out.println("b = " + b.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CountPriorityThreadA extends Thread {
    private int count = 0;

    int getCount() {
        return count;
    }

    @Override
    public void run() {
        while (true) {
            count++;
        }
    }
}

class CountPriorityThreadB extends Thread {
    private int count = 0;

    int getCount() {
        return count;
    }

    @Override
    public void run() {
        while (true) {
            count++;
        }
    }
}