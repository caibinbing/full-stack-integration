package main.java.netty.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 示例：传统网络io —— 客户端发送数据到服务端
 *
 * @author binbing
 * @description 客户端
 */
public class Client {
    public static void main(String[] args) {
        // 服务器
        final String HOST = "localhost";
        // 端口
        final int PORT = 6666;
        final String CONTENT = "Hello Server!";
        Socket socket = null;
        try {
            socket = new Socket(HOST, PORT);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(CONTENT.getBytes(StandardCharsets.UTF_8));
            // 不能调用flush()，否则服务端接收不到数据。
            // outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(socket)) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
