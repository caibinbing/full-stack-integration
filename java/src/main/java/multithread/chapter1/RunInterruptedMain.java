package main.java.multithread.chapter1;

public class RunInterruptedMain {
    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        //interrupted具有清除状态的功能
        System.out.println("是否停止1？" + Thread.interrupted());
        System.out.println("是否停止2？" + Thread.interrupted());
        System.out.println("end!");
    }
}
