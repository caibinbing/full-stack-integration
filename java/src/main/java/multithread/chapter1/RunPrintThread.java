package main.java.multithread.chapter1;

public class RunPrintThread {
    public static void main(String[] args) {
        PrintThread printThread1 = new PrintThread(1);
        PrintThread printThread2 = new PrintThread(2);
        PrintThread printThread3 = new PrintThread(3);
        PrintThread printThread4 = new PrintThread(4);
        PrintThread printThread5 = new PrintThread(5);
        PrintThread printThread6 = new PrintThread(6);
        PrintThread printThread7 = new PrintThread(7);
        PrintThread printThread8 = new PrintThread(8);
        PrintThread printThread9 = new PrintThread(9);
        PrintThread printThread10 = new PrintThread(10);
        PrintThread printThread11 = new PrintThread(11);
        PrintThread printThread12 = new PrintThread(12);
        printThread1.start();
        printThread2.start();
        printThread3.start();
        printThread4.start();
        printThread5.start();
        printThread6.start();
        printThread7.start();
        printThread8.start();
        printThread9.start();
        printThread10.start();
        printThread11.start();
        printThread12.start();
    }
}

class PrintThread extends Thread {
    private int i;

    PrintThread(int i) {
        super();
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(i);
    }
}
