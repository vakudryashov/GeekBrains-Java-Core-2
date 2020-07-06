package Lesson_07.client;

public class ClientApp {
    public static void main(String[] args) {
        try{
            new ChatClient().run();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
