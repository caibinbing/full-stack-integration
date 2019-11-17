package main.java.multithread.chapter3;

import java.util.ArrayList;
import java.util.List;

public class RunTransThread {
    public static void main(String[] args) {
        TransList transList = new TransList();
        TransThreadA transThreadA = new TransThreadA(transList);
        transThreadA.setName("A");
        transThreadA.start();
        TransThreadB transThreadB = new TransThreadB(transList);
        transThreadB.start();
        transThreadB.setName("B");
    }
}

class TransList {
    private List<String> list = new ArrayList<>();

    void add() {
        list.add("CBB");
    }

    int size() {
        return list.size();
    }
}

class TransThreadA extends Thread{
    private TransList transList;

    TransThreadA(TransList transList) {
        this.transList = transList;
    }

    @Override
    public void run() {
        try{
            for (int i = 0; i < 10; i++) {
                transList.add();
                System.out.println("添加了" + (i + 1) + "个元素");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TransThreadB extends Thread{
    private volatile TransList transList;//一定要设置volatile保持可见性，解决bug

    TransThreadB(TransList transList) {
        this.transList = transList;
    }

    @Override
    public void run() {
        try{
            while (true) {
                //有Bug：无法抛异常
                //输出之后就能顺利抛异常
//                System.out.println(transList.size());
                if (transList.size() == 5) {
                    System.out.println("==5了，线程b要退出了！");
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}