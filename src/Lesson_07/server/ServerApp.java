package Lesson_07.server;

public class ServerApp {
    public static void main(String[] args) {
        try {
            new ChatServer().start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
