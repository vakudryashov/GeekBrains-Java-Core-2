package Lesson_07.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {
    private int port = 8189;
    public static Map<String, User> userList = new ConcurrentHashMap<>();

    public ChatServer() {}

    public ChatServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("Соединение установлено");
            new Connection(this, socket);
        }
    }
}
