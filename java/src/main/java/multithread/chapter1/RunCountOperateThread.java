package main.java.multithread.chapter1;

/**
 * Output:
 * CountOperate---begin
 * Thread.currentThread().getName() = main
 * this.getName = Thread-0
 * CountOperate---end
 * run---begin
 * Thread.currentThread().getName() = A
 * this.getName = Thread-0
 * run---end
 */

/**
 * output2:
 * CountOperate---begin
 * Thread.currentThread().getName() = main
 * Thread.currentThread().isAlive() = true
 * this.getName() = Thread-0
 * this.isAlive() = false
 * CountOperate---end
 * main begin t1 isAlive = false
 * main end t1 isAlive = true
 * run---begin
 * Thread.currentThread().getName() = A
 * Thread.currentThread().isAlive() = true
 * this.getName() = Thread-0
 * this.isAlive() = false
 * run---end
 */
public class RunCountOperateThread {
    public static void main(String[] args) {
        CountOperateThread countOperateThread = new CountOperateThread();
        Thread t1 = new Thread(countOperateThread);
        System.out.println("main begin t1 isAlive = " + t1.isAlive());
        t1.setName("A");
        t1.start();
        System.out.println("main end t1 isAlive = " + t1.isAlive());
    }
}

class CountOperateThread extends Thread {
    CountOperateThread() {
        System.out.println("CountOperate---begin");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
        System.out.println("this.getName() = " + this.getName());
        System.out.println("this.isAlive() = " + this.isAlive());
        System.out.println("CountOperate---end");
    }

    @Override
    public void run() {
        System.out.println("run---begin");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
        System.out.println("this.getName() = " + this.getName());
        System.out.println("this.isAlive() = " + this.isAlive());
        System.out.println("run---end");
    }
}