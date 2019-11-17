package main.java.multithread.chapter2;

public class RunPrintStringThread {
    public static void main(String[] args) {
        PrintStringThread printStringThread = new PrintStringThread();
        new Thread(printStringThread).start();
        System.out.println("我要停止它！stopThread = " + Thread.currentThread().getName());
        printStringThread.setContinuePrint(false);
    }
}

class PrintStringThread implements Runnable {
    private boolean isContinuePrint = true;

    public boolean isContinuePrint() {
        return isContinuePrint;
    }

    public void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }

    void printStringMedhod() {
        try{
            while (isContinuePrint) {
                System.out.println("run printStrintMethod threadName = " + Thread.currentThread().getName());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        printStringMedhod();
    }
}
