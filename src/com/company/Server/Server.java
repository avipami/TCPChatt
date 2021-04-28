package com.company.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server {

    private ServerUsers serverUsers;
    private int port;


    Server(int port){
        this.port = port;
        //ServerUsers serverUsers = ServerUsers.getInstance();

        try(ServerSocket serverSocket = new ServerSocket(this.port)) {

            while(true){
                Socket socket = serverSocket.accept();
                User user = new User(socket);
                Thread userThread = new Thread(user);
                userThread.start();
            }
        } catch (IOException e) {
            System.out.println("Server failed to connect");
        }

    }

    public static void main(String[] args){
        new Server(5566);


    }


}
