package Lesson_07.server;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class User {
    public String nick;
    private String password;
    private final Connection connection;

    public User(String nick, Connection connection) {
        this.nick = nick;
        this.connection = connection;
    }

    public void runCmd(String cmd, String msg){
        if(cmd.equals("/password")){
            authenticate(msg);
        }else if(cmd.equals("/publicMsg")){
            sendBroadcastMsg(String.format("/publicMsg %s: %s",nick, msg));
        }else if (cmd.equals("/privateMsg")){
            String[] chunk = msg.split(" ",2);
            String message = String.format("/publicMsg Лично от %s: %s",nick,chunk[1]);
            ChatServer.userList.get(chunk[0]).sendMsg(message);
            sendMsg("/publicMsg Лично для "+chunk[0]+": "+chunk[1]);
        }else if(cmd.equals("/exit")){
            die();
            connection.close();
        }
    }
    public void authenticate(){
        if (ChatServer.userList.containsKey(nick)){
            sendAccessDeny(String.format("Пользователь \"%s\" уже присутствует в чате.",nick));
            connection.getUser();
        }else if (isNickRegistered()){
            sendRequestPsw(String.format("Пользователь \"%s\" зарегистрирован. Требуется ввод пароля",nick));
        }else{
            enterChat();
        }
    }

    public void authenticate(String userPassword) {
        if (userPassword.equals(password)){
            enterChat();
        }else{
            sendAccessDeny("Неправильный пароль или nickname. Попробуй ещё раз");
            connection.getUser();
        }
    }

    private boolean isNickRegistered(){
        boolean result = false;
        String fileName = "./src/Lesson_07/server/registration.txt";
        Path path = Paths.get(fileName);
        try {
            Scanner scanner = new Scanner(path);
            scanner.useDelimiter(System.lineSeparator());
            while (scanner.hasNext()){
                String[] part = scanner.nextLine().split(" :: ");
                System.out.println(Arrays.toString(part));
                if (part[0].equals(nick)){
                    password = part[1];
                    result = true;
                    break;
                }
            }
            scanner.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void enterChat(){
        sendUserList();
        ChatServer.userList.put(nick, this);
        sendAddUser(nick);
    }

    public void die(){
        ChatServer.userList.remove(nick);
        sendRemoveUser(nick);
    }

    public void sendMsg(String msg){
        connection.send(msg);
    }

    public void sendAccessDeny(String msg){
        sendMsg(String.format("/accessDeny %s", msg));
    }

    public void sendRequestPsw(String msg){
        sendMsg(String.format("/requestPassword %s", msg));
    }

    private void sendUserList(){
        sendMsg("/userList "+ ChatServer.userList.keySet().toString());
    }

    private void sendAddUser(String nickname){
        sendBroadcastMsg("/addUser "+nickname);
    }

    private void sendRemoveUser(String nickname){
        sendBroadcastMsg("/removeUser "+nickname);
    }

    public void sendBroadcastMsg(String msg){
        HashMap<String, User> map = new HashMap<>(ChatServer.userList);
        for (Map.Entry<String, User> entry :map.entrySet()) {
            User anyUser = entry.getValue();
            anyUser.sendMsg(msg);
        }
    }
}
