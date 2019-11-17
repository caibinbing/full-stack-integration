package main.java.multithread.chapter1;

public class RunNoSameValueThread {
    public static void main(String[] args) {
        final NoSameValueObject obj = new NoSameValueObject();
        Thread thread1 = new Thread(() -> obj.setValue("a", "aa"));
        thread1.setName("a");
        thread1.start();
        Thread thread2 = new Thread(obj::printUsernamePassword);
        thread2.start();
    }
}

class NoSameValueObject{
    private String username = "1";
    private String password = "11";

    void setValue(String u, String p) {
        this.username = u;
        if ("a".equals(Thread.currentThread().getName())) {
            System.out.println("停止a线程");
            Thread.currentThread().suspend();
        }
        //挂起后，p值无法写入
        this.password = p;
    }

    void printUsernamePassword() {
        System.out.println(username + " " + password);
    }
}