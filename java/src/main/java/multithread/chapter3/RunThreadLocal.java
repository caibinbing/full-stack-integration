package main.java.multithread.chapter3;

public class RunThreadLocal {
    static ThreadLocal threadLocal = new ThreadLocal();
    public static void main(String[] args) {
        if (threadLocal.get() == null) {
            System.out.println("no value");
            threadLocal.set("new value");
        }
        System.out.println(threadLocal.get());
        System.out.println(threadLocal.get());
    }
}
