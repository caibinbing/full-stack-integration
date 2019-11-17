package main.java.multithread.chapter2;

import java.util.ArrayList;
import java.util.List;

/*
    出现脏读。原因：两个线程以异步的方式返回list参数的size()。
 */
public class RunDirtyReadListThread {
    public static void main(String[] args) throws InterruptedException {
        DirtyReadList dirtyReadList = new DirtyReadList();
        DirtyReadListThreadA dirtyReadListThreadA = new DirtyReadListThreadA(dirtyReadList);
        dirtyReadListThreadA.setName("A");
        dirtyReadListThreadA.start();
        DirtyReadListThreadB dirtyReadListThreadB = new DirtyReadListThreadB(dirtyReadList);
        dirtyReadListThreadB.setName("B");
        dirtyReadListThreadB.start();
        Thread.sleep(6000);
        System.out.println("listSize = " + dirtyReadList.getSize());
    }
}

class DirtyReadList {
    private List<String> list = new ArrayList<>();

    synchronized void add(String data) {
        list.add(data);
    }

    synchronized int getSize() {
        return list.size();
    }
}

class DirtyReadListService {
    //list参数对象在项目中是一份实例，是单例的
    DirtyReadList addServiceMethod(DirtyReadList list, String data) {
        try {
            //改
            synchronized (list) {
                if (list.getSize() < 1) {
                    //模拟从远程话费2秒取回数据
                    Thread.sleep(2000);
                    list.add(data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }
}

class DirtyReadListThreadA extends Thread {
    private DirtyReadList dirtyReadList;

    DirtyReadListThreadA(DirtyReadList dirtyReadList) {
        this.dirtyReadList = dirtyReadList;
    }

    @Override
    public void run() {
        DirtyReadListService dirtyReadListService = new DirtyReadListService();
        dirtyReadListService.addServiceMethod(dirtyReadList, "A");
    }
}

class DirtyReadListThreadB extends Thread {
    private DirtyReadList dirtyReadList;

    DirtyReadListThreadB(DirtyReadList dirtyReadList) {
        this.dirtyReadList = dirtyReadList;
    }

    @Override
    public void run() {
        DirtyReadListService dirtyReadListService = new DirtyReadListService();
        dirtyReadListService.addServiceMethod(dirtyReadList, "B");
    }
}