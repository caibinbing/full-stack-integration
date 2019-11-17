package main.java.multithread.chapter2;

//使用jps查到进城ID，然后使用jstack -l ID可以查看死锁情况。
//只要互相等待对方释放就有可能出现死锁
public class RunDeadThread {
    public static void main(String[] args) {
        try {
            DeadThread deadThread = new DeadThread();
            deadThread.setFlag("a");
            Thread threadA = new Thread(deadThread);
            threadA.start();
            Thread.sleep(100);
            deadThread.setFlag("b");
            Thread threadB = new Thread(deadThread);
            threadB.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class DeadThread implements Runnable {
    String username;
    Object lock1 = new Object();
    Object lock2 = new Object();

    void setFlag(String username) {
        this.username = username;
    }


    @Override
    public void run() {
        if ("a".equals(username)) {
            synchronized (lock1) {
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("按lock1->lock2代码顺序执行了");
                }
            }

        }
        if ("b".equals(username)) {
            synchronized (lock2) {
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("按lock2->lock1代码顺序执行了");
                }
            }
        }
    }
}
