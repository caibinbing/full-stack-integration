package main.java.multithread.chapter6;

import java.io.*;

//序列化与反序列化的单例模式实现
public class RunSerializableSingleton {
    public static void main(String[] args) {
        try {
            SerialObj serialObj = SerialObj.getInstance();
            FileOutputStream fos = new FileOutputStream(new File("mySerialObj.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(serialObj);
            oos.close();
            fos.close();
            System.out.println(serialObj.hashCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fis = new FileInputStream(new File("mySerialObj.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            SerialObj serialObj = (SerialObj) ois.readObject();
            ois.close();
            fis.close();
            System.out.println(serialObj.hashCode());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class SerialObj implements Serializable {

    private static final long serialVersionUID = -1303470296869512716L;

    private SerialObj() {

    }

    //内部类方式
    private static class SerialObjHandler {
        private static final SerialObj serialObj = new SerialObj();
    }

    static SerialObj getInstance() {
        return SerialObjHandler.serialObj;
    }

    //加上以下代码才实现序列化和反序列化单例
    //当JVM从内存中反序列化地"组装"一个新对象时,就会自动调用这个 readResolve方法来返回我们指定好的对象
    //readResolve方法会在readObject之后调用，所以反序列化的时候readResolve方法会覆盖掉readObject方法的修改
    Object readResolve() throws ObjectStreamException {
        System.out.println("调用了readResolve方法");
        return SerialObjHandler.serialObj;
    }

}
