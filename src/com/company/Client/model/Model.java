package com.company.Client.model;

import com.company.LoginObject;
import com.company.UsersOnline;

import java.util.ArrayList;
import java.util.List;

public class Model {
    String userName;
    UsersOnline userList;

    public Model(String userName){

        this.userName = userName;
        this.userList = new UsersOnline();
        //userList.add(userName);
    }

    public String GetName(){
        return this.userName;
    }

    public void NewUserConnected(UsersOnline object){
        userList = object;
    }
}
