package com.company.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerUsers {

    private static ServerUsers serverUsersInstance = null;

    public Map<String , User> users;

    private ServerUsers(){
     users = new HashMap<String, User>();
    }

    public static ServerUsers getInstance(){
        if(serverUsersInstance == null){
            serverUsersInstance = new ServerUsers();
        }
        return serverUsersInstance;
    }

    public void Add(User user){
       this.users.put(user.getName(), user);
    }

    public List GetUserList(){
        List userList = new ArrayList();
        for (Map.Entry<String,User> entry : users.entrySet())
            userList.add(entry.getKey());

        return userList;
    }

}

