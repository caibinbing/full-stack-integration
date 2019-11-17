package main.java.multithread.chapter2;

import java.util.ArrayList;
import java.util.List;

public class RunRandomCallThread {
    public static void main(String[] args) {
        //同步块中的代码是同步打印的，但线程A和线程B的执行是异步的，可能出现脏读的环境
        RandomCallList randomCallList = new RandomCallList();
        RandomCallThreadA randomCallThreadA = new RandomCallThreadA(randomCallList);
        randomCallThreadA.setName("A");
        randomCallThreadA.start();
        RandomCallThreadB randomCallThreadB = new RandomCallThreadB(randomCallList);
        randomCallThreadB.setName("B");
        randomCallThreadB.start();
    }
}

class RandomCallList {
    private List<String> list = new ArrayList<>();

    synchronized void add(String username) {
        System.out.println("Thread Name = " + Thread.currentThread().getName()
                + "执行了add方法");
        list.add(username);
        System.out.println("Thread Name = " + Thread.currentThread().getName()
                + "退出了add方法");
    }

    synchronized int getSize() {
        System.out.println("Thread Name = " + Thread.currentThread().getName()
                + "执行了getSize方法");
        int sizeValue = list.size();
        System.out.println("Thread Name = " + Thread.currentThread().getName()
                + "退出了getSize方法");
        return sizeValue;
    }
}

class RandomCallThreadA extends Thread {
    private RandomCallList randomCallList;

    RandomCallThreadA(RandomCallList randomCallList) {
        this.randomCallList = randomCallList;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            randomCallList.add("thradA" + (i + 1));
        }
    }
}

class RandomCallThreadB extends Thread {
    private RandomCallList randomCallList;

    RandomCallThreadB(RandomCallList randomCallList) {
        this.randomCallList = randomCallList;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            randomCallList.add("thradB" + (i + 1));
        }
    }
}