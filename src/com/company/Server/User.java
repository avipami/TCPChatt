package com.company.Server;

import com.company.LoginObject;
import com.company.LogoutObject;
import com.company.MessageObject;
import com.company.UsersOnline;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class User implements Runnable {
    public String name;
    public Socket socket;
    public ObjectInputStream objectInputStream;
    public ObjectOutputStream objectOutputStream;

    User(Socket socket) throws IOException {
        this.socket = socket;
        this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());
    }
    public String getName(){
        return this.name;
    }
    public ObjectOutputStream GetOutputStream(){
    return this.objectOutputStream;
}

    public void SendObject(Object object){

       for (Map.Entry<String, User> entry : ServerUsers.getInstance().users.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
            try {
                entry.getValue().objectOutputStream.writeObject(object);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object object = objectInputStream.readObject();

                if (object instanceof LoginObject) {
                    this.name = ((LoginObject) object).GetUserName();
                    System.out.println(this.name);
                    ServerUsers.getInstance().users.put(this.name, this);
                    SendObject(object);
                }
                if (object instanceof UsersOnline){

                }
                if(object instanceof MessageObject){
                    System.out.println(((MessageObject) object).GetMessage());
                    SendObject(object);
                }
                if (object instanceof LogoutObject){
                    SendObject(object);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
