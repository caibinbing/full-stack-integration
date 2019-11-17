package main.java.multithread.chapter1;

public class RunSynchronizedObjectThread {
    public static void main(String[] args) {
        try{
            SynchronizedObject object = new SynchronizedObject();
            SynchronizedObjectThread synchronizedObjectThread = new SynchronizedObjectThread(object);
            synchronizedObjectThread.start();
            Thread.sleep(500);
            synchronizedObjectThread.stop();
            System.out.println(object.getUsername() + " " + object.getPassword());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
