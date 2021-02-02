package main.java.netty.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * 示例：传统网络io —— 服务端接收客户端数据并输出
 *
 * @author binbing
 * @description 服务端
 */
public class Server {
    public static void main(String[] args) {
        // 端口号，缓存大小
        final int PORT = 6666, BUFFER_SIZE = 2048;
        // 哨兵
        int read;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            while (-1 != (read = inputStream.read(buffer))) {
                System.out.println(new String(buffer, 0, read));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(serverSocket)) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
