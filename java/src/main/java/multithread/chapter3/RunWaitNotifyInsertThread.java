package main.java.multithread.chapter3;

public class RunWaitNotifyInsertThread {
    public static void main(String[] args) {
        DBTools dbTools = new DBTools();
        for (int i = 0; i < 20; ++i) {
            BackupA backupA = new BackupA(dbTools);
            backupA.start();
            BackupB backupB = new BackupB(dbTools);
            backupB.start();
        }
    }
}

class DBTools {
    //作插入标记
    private volatile boolean prevIsA = false;

    synchronized void backupA() {
        try {
            while (prevIsA) {
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("AAAAA");
            }
            prevIsA = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void backupB() {
        try {
            while (!prevIsA) {
                wait();
            }
            for (int i = 0; i < 5; ++i) {
                System.out.println("BBBBB");
            }
            prevIsA = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BackupA extends Thread {
    private DBTools dbTools;

    BackupA(DBTools dbTools) {
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        dbTools.backupA();
    }
}

class BackupB extends Thread {
    private DBTools dbTools;

    BackupB(DBTools dbTools) {
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        dbTools.backupB();
    }
}