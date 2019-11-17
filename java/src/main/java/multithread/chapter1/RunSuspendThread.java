package main.java.multithread.chapter1;

public class RunSuspendThread {
    public static void main(String[] args) {
        try{
            SuspendThread suspendThread = new SuspendThread();
            suspendThread.start();
            Thread.sleep(5000);
            //A
            suspendThread.suspend();
            System.out.println("A = " + System.currentTimeMillis() + " i = " + suspendThread.getI());
            Thread.sleep(5000);
            System.out.println("A = " + System.currentTimeMillis() + " i = " + suspendThread.getI());
            //B
            suspendThread.resume();
            Thread.sleep(5000);
            //C
            suspendThread.suspend();
            System.out.println("B = " + System.currentTimeMillis() + " i = " + suspendThread.getI());
            Thread.sleep(5000);
            System.out.println("B = " + System.currentTimeMillis() + " i = " + suspendThread.getI());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SuspendThread extends Thread {
    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        while (true) {
            ++i;
        }
    }
}
