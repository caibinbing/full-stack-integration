package main.java.multithread.chapter3;

public class RunThreadLocalExt {
    public static void main(String[] args) {
        ThreadLocalExt threadLocalExt = new ThreadLocalExt();
        if (threadLocalExt.get() == null) {
            System.out.println("no value");
            threadLocalExt.set("new value");
        }
        System.out.println(threadLocalExt.get());
        System.out.println(threadLocalExt.get());
    }
}

class ThreadLocalExt extends ThreadLocal {
    @Override
    protected Object initialValue() {
        return "initial value!";
    }
}
