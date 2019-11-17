package main.java.multithread.chapter2;

public class RunDirtyReadThread {
    public static void main(String[] args) {
        try {
            PublicVar publicVar = new PublicVar();
            DirtyReadThread dirtyReadThread = new DirtyReadThread(publicVar);
            dirtyReadThread.start();
            Thread.sleep(200);
            publicVar.getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class PublicVar {
    String username = "A";
    String password = "AA";

    //获得的是对象锁。如果要调用其他synchronized类型的方法需要等此方法释放对象锁。
    synchronized void setValue(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("setValue method thread name = " + Thread.currentThread().getName()
                    + " username = " + username + " password = " + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void getValue() {
        System.out.println("getValue method thread name = " + Thread.currentThread().getName()
                + " username = " + username + " password = " + password);
    }
}

class DirtyReadThread extends Thread {
    private PublicVar publicVar;

    DirtyReadThread(PublicVar publicVar) {
        this.publicVar = publicVar;
    }

    @Override
    public void run() {
        super.run();
        publicVar.setValue("B", "BB");
    }
}