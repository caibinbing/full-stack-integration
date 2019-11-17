package main.java.test;

import org.apache.storm.utils.BufferInputStream;

import java.io.*;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFile {
    public static void main(String[] args) {
        //Paths 路径操作 NIO包
        //Files 文件操作 NIO包
        //File IO包操作
        try {
            FileInputStream in = new FileInputStream("src");
            FileOutputStream out = new FileOutputStream("dist");
            byte[] buffer = new byte[20 * 1024];
            int cnt ;
            while ((cnt = in.read(buffer, 0, buffer.length)) != -1) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedInputStream bufferedInputStream;
        DataInputStream dataInputStream;
        //字符操作
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("src");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line ;
        while (true) {
            try {
                if ((line = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //序列号&反序列化
        ObjectInputStream objectInputStream;

    }
}
