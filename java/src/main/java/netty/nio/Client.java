package main.java.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Objects;

/**
 * 示例：nio —— 复制文件
 *
 * @author binbing
 * @description 客户端
 * @date 2021/1/31 11:54 AM
 */
public class Client {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", 6666));
            socketChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.wrap("Hello Server!".getBytes());
            socketChannel.write(byteBuffer);
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(socketChannel)) {
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
