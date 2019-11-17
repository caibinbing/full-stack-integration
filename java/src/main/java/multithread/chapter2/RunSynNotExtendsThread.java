package main.java.multithread.chapter2;

public class RunSynNotExtendsThread {
    public static void main(String[] args) {
        SysNotExtendsSub sysNotExtendsSub = new SysNotExtendsSub();
        SysNotExtendsThreadA sysNotExtendsThreadA = new SysNotExtendsThreadA(sysNotExtendsSub);
        sysNotExtendsThreadA.setName("A");
        sysNotExtendsThreadA.start();
        SysNotExtendsThreadB sysNotExtendsThreadB = new SysNotExtendsThreadB(sysNotExtendsSub);
        sysNotExtendsThreadB.setName("B");
        sysNotExtendsThreadB.start();
    }
}

class SynNotExtendsMain {
    synchronized void serviceMethod() {
        try {
            System.out.println("int main next step sleep begin threadName = "
                    + Thread.currentThread().getName() + " time = "
                    + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("int main next step sleep end threadName = "
                    + Thread.currentThread().getName() + " time = "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SysNotExtendsSub extends SynNotExtendsMain {
    @Override
    void serviceMethod() {
        try {
            System.out.println("int sub next step sleep begin threadName = "
                    + Thread.currentThread().getName() + " time = "
                    + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("int sub next step sleep end threadName = "
                    + Thread.currentThread().getName() + " time = "
                    + System.currentTimeMillis());
            //同步不可以继承
            super.serviceMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SysNotExtendsThreadA extends Thread {
    private SysNotExtendsSub sysNotExtendsSub;

    SysNotExtendsThreadA(SysNotExtendsSub sysNotExtendsSub) {
        this.sysNotExtendsSub = sysNotExtendsSub;
    }

    @Override
    public void run() {
        sysNotExtendsSub.serviceMethod();
    }
}

class SysNotExtendsThreadB extends Thread {
    private SysNotExtendsSub sysNotExtendsSub;

    SysNotExtendsThreadB(SysNotExtendsSub sysNotExtendsSub) {
        this.sysNotExtendsSub = sysNotExtendsSub;
    }

    @Override
    public void run() {
        sysNotExtendsSub.serviceMethod();
    }
}