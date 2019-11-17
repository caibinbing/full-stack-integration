package main.java.multithread.chapter3;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

//字节流管道通信
public class RunPipeIOThread {
    public static void main(String[] args) {
        try {
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();
            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();
            //通信链接，这样才可以将数据进行输出与输入
            outputStream.connect(inputStream);
            ReadThread readThread = new ReadThread(readData, inputStream);
            readThread.start();
            Thread.sleep(2000);
            WriteThread writeThread = new WriteThread(writeData, outputStream);
            writeThread.start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WriteData {
    void writeMethod(PipedOutputStream out) {
        try {
            System.out.println("write : ");
            for (int i = 0; i < 300; ++i) {
                String outData = (i + 1) + " ";
                out.write(outData.getBytes());
                System.out.println(outData);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadData {
    void readMethod(PipedInputStream input) {
        try {
            System.out.println("read : ");
            //buf控制了每行输出多少个字节
            byte[] buf = new byte[30];
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

class WriteThread extends Thread {
    private WriteData writeData;
    private PipedOutputStream outputStream;

    WriteThread(WriteData writeData, PipedOutputStream outputStream) {
        this.writeData = writeData;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        writeData.writeMethod(outputStream);
    }
}

class ReadThread extends Thread {
    private PipedInputStream inputStream;
    private ReadData readData;

    ReadThread(ReadData readData, PipedInputStream inputStream) {
        this.inputStream = inputStream;
        this.readData = readData;
    }

    @Override
    public void run() {
        readData.readMethod(inputStream);
    }
}