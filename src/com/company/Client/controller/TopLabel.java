package com.company.Client.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopLabel extends JLabel implements ActionListener {

    private static TopLabel topLabelInstance = null;

    public JLabel topLabel;

    private TopLabel(){

        this.topLabel =  new JLabel();
    }

    public static TopLabel getInstance(){
        if (topLabelInstance == null) {
            topLabelInstance = new TopLabel();
        }
        return topLabelInstance;
    }
    public void setLabelText(String labeltext){
        this.topLabel.setText(labeltext);
    }
    @Override
    public void actionPerformed(ActionEvent e) {


    }


}
