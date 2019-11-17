package main.java.multithread.chapter3;

import java.io.*;

//字符流管道通信
public class RunPipeRWThread {
    public static void main(String[] args) {
        try {
            DataWriter dataWriter = new DataWriter();
            DataReader dataReader = new DataReader();
            PipedReader input = new PipedReader();
            PipedWriter out = new PipedWriter();
            out.connect(input);
            ThreadRead threadRead = new ThreadRead(dataReader, input);
            threadRead.start();
            Thread.sleep(2000);
            ThreadWrite threadWrite = new ThreadWrite(dataWriter, out);
            threadWrite.start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class DataWriter {
    void writeMethod(PipedWriter out) {
        try {
            System.out.println("write : ");
            for (int i = 0; i < 300; ++i) {
                String outData = (i + 1) + " ";
                out.write(outData);
                System.out.println(outData);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DataReader {
    void readMethod(PipedReader input) {
        try {
            System.out.println("read : ");
            //buf控制了每行输出多少个字节
            char[] buf = new char[30];
            int len = input.read(buf);
            while (len != -1) {
                String newData = new String(buf, 0, len);
                System.out.println(newData);
                len = input.read(buf);
            }
            System.out.println();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadWrite extends Thread {
    private DataWriter writeData;
    private PipedWriter pipedWriter;

    ThreadWrite(DataWriter writeData, PipedWriter pipedWriter) {
        this.writeData = writeData;
        this.pipedWriter = pipedWriter;
    }

    @Override
    public void run() {
        writeData.writeMethod(pipedWriter);
    }
}

class ThreadRead extends Thread {
    private PipedReader inputStream;
    private DataReader readData;

    ThreadRead(DataReader readData, PipedReader inputStream) {
        this.inputStream = inputStream;
        this.readData = readData;
    }

    @Override
    public void run() {
        readData.readMethod(inputStream);
    }
}