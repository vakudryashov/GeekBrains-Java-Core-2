package Lesson_06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class LightChat {
    private final boolean server;
    private final String server_addr;
    private final int server_port;
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        try{
            new LightChat(false).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LightChat(boolean server) {
        this.server = server;
        this.server_port = 8189;
        this.server_addr = "localhost";
    }
    public LightChat(boolean server, int server_port) {
        this.server = server;
        this.server_port = server_port;
        this.server_addr = "localhost";
    }

    public LightChat(boolean server, String server_addr, int server_port) {
        this.server = server;
        this.server_addr = server_addr;
        this.server_port = server_port;
    }

    public void start() throws IOException, InterruptedException { openConnection(); }

    private void openConnection() throws IOException, InterruptedException {
        if (server) {
            serverSocket = new ServerSocket(server_port);
            System.out.println("Сервер запущен, ожидаем подключения...");
        }
        new Thread(this::sendMessages).start();
        while(true) {
            try {
                if (server){
                    socket = serverSocket.accept();
                    System.out.println("Клиент подключился");
                }else{
                    socket = new Socket(server_addr, server_port);
                    System.out.println("Соединение с сервером установлено");
                }
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                receiveMessages();
            }catch(Exception e){
                System.out.println(server ? "Клиент отключился." : "Сервер недоступен. Ждём 10 секунд...");
                try {
                    if (in != null) in.close();
                    if (out != null) out.close();
                    if (socket != null) socket.close();
                }catch(Exception e1){ e.printStackTrace(); }
                if (!server) Thread.sleep(10000);
            }
        }
    }

    private void sendMessages(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            try {
                String msg = scanner.nextLine();
                out.writeUTF(msg);
            }catch(Exception e){
                System.out.println("Не удалось отправить сообщение. Причина: "+e.getMessage());
            }
        }
    }

    private void receiveMessages() throws IOException {
        while (true) {
            String msg = in.readUTF();
            System.out.printf("Сообщение от %s: %s%n",server ? "клиента" : "сервера",msg);
        }
    }
}
