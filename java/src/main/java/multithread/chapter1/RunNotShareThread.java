package main.java.multithread.chapter1;

public class RunNotShareThread {
    public static void main(String[] args) {
        NotShareThread a = new NotShareThread("A");
        NotShareThread b = new NotShareThread("B");
        NotShareThread c = new NotShareThread("C");
        NotShareThread d = new NotShareThread("D");
        a.start();
        b.start();
        c.start();
        d.start();
    }
}

class NotShareThread extends Thread {
    private int count = 5;

    NotShareThread(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        while (count > 0) {
            count--;
            System.out.println("Thread : " + currentThread().getName() + " ,count = " + count);
        }
    }
}
