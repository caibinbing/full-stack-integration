package main.java.multithread.chapter3;

public class RunFirstNotify {
    public static void main(String[] args) throws InterruptedException {
        FirstNotify firstNotify = new FirstNotify();
        //通知过早！
        Thread a = new Thread(firstNotify.runnableA);
        a.start();
        Thread.sleep(100);
        Thread b = new Thread(firstNotify.runnableB);
        b.start();
    }
}

class FirstNotify {
    private final String lock = "";
    //防止通知过早，wait阻塞
    private boolean isFirstRunB = false;
    Runnable runnableA = () -> {
        try {
            synchronized (lock) {
                while (!isFirstRunB) {
                    System.out.println("begin wait");
                    lock.wait();
                    System.out.println("end wait");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };
    Runnable runnableB = () -> {
        synchronized (lock) {
            System.out.println("begin notify");
            lock.notify();
            System.out.println("end notify");
            isFirstRunB = true;
        }
    };

}