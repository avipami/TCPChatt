package com.company.Client.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MSendButton extends JButton implements ActionListener {

    private static MSendButton MSendButtonInstance = null;

    public JButton sendButton;

    private MSendButton(){
        this.sendButton =  new JButton("Send");
    }

    public static MSendButton getInstance(){
        if (MSendButtonInstance == null) {
            MSendButtonInstance = new MSendButton();
        }
        return MSendButtonInstance;
    }
    @Override
    public void actionPerformed(ActionEvent e) {


    }


}
