package main.java.multithread.chapter3;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//生产和消费交替执行
public class RunPCStackThread {
    public static void main(String[] args) {
        PCStack pcStack = new PCStack();
        PCStackProducer producer = new PCStackProducer(pcStack);
        PCStackConsumer consumer = new PCStackConsumer(pcStack);
        PThread pThread = new PThread(producer);
        pThread.start();
        CThread cThread = new CThread(consumer);
        cThread.start();
    }
}

class PCStack {
    private List<String> list = new ArrayList<>();

    //使用synchronized同步，锁是同一个对象
    synchronized void push() {
        try {
            /*
                size的值不会大于1
                此处问题：当条件改变时并没有得到及时的响应
                多个呈wait状态的线程被唤醒，继而执行list.remove(0)出现异常。
                解决：if换成while
             */
            if (list.size() == 1) {
                this.wait();
            }
            list.add("anyString = " + LocalTime.now());
            //还是需要notifyAll()，否则会假死
            this.notify();
            System.out.println("push = " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized String pop() {
        String sReturn = "";
        try {
            if (list.size() == 0) {
                System.out.println("pop operation : " + Thread.currentThread().getName() + " is wait");
                this.wait();
            }
            sReturn = list.get(0).toString();
            list.remove(0);
            //还是需要notifyAll()
            this.notify();
            System.out.println("pop = " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sReturn;
    }
}

class PThread extends Thread {
    private PCStackProducer producer;

    PThread(PCStackProducer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        while (true) {
            producer.pushService();
        }
    }
}

class CThread extends Thread {
    private PCStackConsumer consumer;

    CThread(PCStackConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true) {
            consumer.popService();
        }
    }
}

class PCStackProducer {
    private PCStack pcStack;

    PCStackProducer(PCStack pcStack) {
        this.pcStack = pcStack;
    }

    void pushService() {
        pcStack.push();
    }
}

class PCStackConsumer {
    private PCStack pcStack;

    PCStackConsumer(PCStack pcStack) {
        this.pcStack = pcStack;
    }

    void popService() {
        System.out.println("pop = " + pcStack.pop());
    }
}