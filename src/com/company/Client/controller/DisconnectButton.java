package com.company.Client.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisconnectButton extends JButton implements ActionListener {

    private static DisconnectButton disconnectButtonInstance = null;

    public JButton disconnectButton;

    private DisconnectButton(){
        this.disconnectButton =  new JButton("Send");
    }

    public static DisconnectButton getInstance(){
        if (disconnectButtonInstance == null) {
            disconnectButtonInstance = new DisconnectButton();
        }
        return disconnectButtonInstance;
    }
    @Override
    public void actionPerformed(ActionEvent e) {


    }


}
