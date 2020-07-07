package Lesson_07.server;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class Connection {
    private ChatServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Queue<String> outputBuffer = new LinkedList<>();
    private boolean isOutBufferBusy = false;
    private User user;

    public Connection(ChatServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
        new Thread(this::getIOStream).start();
    }

    private void getIOStream(){
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            getUser();
            while (true) {
                String msg = in.readUTF();
                handleMessage(msg);
            }
        }catch(EOFException eofException){
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (user != null) user.die();
            close(in,out,socket);
        }
    }

    private void handleMessage(String msg){
        String[] part = msg.split(" ",2);
        String cmd = part[0];
        msg = part.length>1 ? part[1] : "";
        if (cmd.equals("/nickname")){
            String nickname = part[1];
            if (user != null) { user.die(); }
            user = new User(nickname, this);
            user.authenticate();
        }else if (user != null){
            user.runCmd(cmd, msg);
        }
    }

    public void getUser(){
        send("/userList "+ ChatServer.userList.keySet().toString());
        send("/requestNickname");
    }

    public void send(String msg){
        outputBuffer.offer(msg);
        if (isOutBufferBusy) return;
        isOutBufferBusy = true;
        try{
            while((msg = outputBuffer.poll()) != null) {
                out.writeUTF(msg);
                out.flush();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        isOutBufferBusy = false;
    }


    public void close(Closeable...items){
        user = null;
        try {
            for (Closeable item : items) {
                if (item != null) item.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
