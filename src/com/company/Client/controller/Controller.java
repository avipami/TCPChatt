package com.company.Client.controller;

import com.company.Client.model.Model;
import com.company.Client.view.Viewen;
import com.company.LoginObject;
import com.company.LogoutObject;
import com.company.MessageObject;
import com.company.UsersOnline;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;



public class Controller implements Runnable {
    Socket socket;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    Model user;

    public Controller(String ip, int port, Model user) {
        try {
            System.out.println("controller started");
            this.user = user;
            this.socket = new Socket(ip, port);
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        LoginObject login = new LoginObject(this.user.GetName());
        SendObject(login);
        while(true){
            try {
                Object object;
                object = objectInputStream.readObject();

                if (object instanceof LoginObject) {
                   // user.NewUserConnected((LoginObject) object);
                    System.out.println(((LoginObject) object).GetUserName());
                    MUserList.getInstance().append(((LoginObject) object).GetUserName()+"\n");
                    //this.name = ((LoginObject) object).getUserName();
                    //SendObject(object);
                }
                if(object instanceof MessageObject){
                    System.out.println(((MessageObject) object).GetMessage());
                    MTextArea.getInstance().append(((MessageObject) object).GetMessage() + "\n");

                    //SendObject(object);
                }
                if (object instanceof LogoutObject){
                    SendObject(object);
                }
                if (object instanceof UsersOnline){

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void SendObject(Object object) {
        try {
            this.objectOutputStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket GetSocket(){
        return this.socket;
    }
}