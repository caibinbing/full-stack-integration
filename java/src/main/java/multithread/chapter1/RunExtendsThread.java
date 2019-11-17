package main.java.multithread.chapter1;

public class RunExtendsThread {
    /**
     * output:
     * ending...!
     * ExtendsThread
     * <p>
     * three times extendsThread.start() throws
     * IllegalThreadStateException
     */
    public static void main(String[] args) {
        ExtendsThread extendsThread = new ExtendsThread();
        extendsThread.start();
        System.out.println("ending...!");
    }
}

class ExtendsThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("ExtendsThread");
    }
}
