package main.java.multithread.chapter1;

public class SynchronizedObjectThread extends Thread {
    private SynchronizedObject object;

    SynchronizedObjectThread(SynchronizedObject object) {
        super();
        this.object = object;
    }

    @Override
    public void run() {
        object.printString("b", "bb");
    }
}

class SynchronizedObject{
    private String username = "a";
    private String password = "aa";

    String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    synchronized void printString(String username, String password) {
        try{
            this.username = username;
            Thread.sleep(100000);
            this.password = password;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
