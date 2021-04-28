package com.company.Client.view;

import com.company.Client.controller.Controller;
import com.company.Client.controller.MTextArea;
import com.company.Client.controller.MUserList;
import com.company.Client.model.Model;
import com.company.MessageObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.ScrollPaneConstants.*;


public class Viewen extends JFrame implements ActionListener {
    JPanel top = new JPanel();
    JPanel center = new JPanel();
    JPanel right = new JPanel();
    JPanel bottom = new JPanel();

    JLabel topLabel = new JLabel();
    JButton logoutButton = new JButton("Logout");
    JTextField text = new JTextField(50);
    MUserList userList = MUserList.getInstance();
    MTextArea textarea = MTextArea.getInstance(); //25, 55
    JScrollPane scrollPane = new JScrollPane(textarea);
    JButton sendButton = new JButton("Send");

    private boolean newMessageStatus = false;
    private boolean connectedStatus = false;

    Controller controller;
    String inText;

    public Viewen(Model user, Controller controller) {
        this.controller = controller;
        this.setTitle("Chattaway");
        topLabel.setText(user.GetName() + " chattar");

        logoutButton.addActionListener(this);
        top.add(logoutButton);
        top.add(topLabel);

        textarea.setEditable(false);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setRows(25);
        textarea.setColumns(47);
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        center.add(scrollPane);

        userList.setEditable(false);
        userList.setLineWrap(true);
        userList.setWrapStyleWord(true);
        userList.setColumns(20);
        userList.setRows(12);
        right.add(userList);

        sendButton.addActionListener(this);
        text.addActionListener(this);
        bottom.add(text);
        bottom.add(sendButton);

        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(right, BorderLayout.EAST);
        this.add(bottom, BorderLayout.SOUTH);
        text.requestFocusInWindow();
        setSize(800, 600);
        setLocation(250, 250);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                text.requestFocus();
            }
        });
    }


    public synchronized boolean GetNewMessageStatus() {
        return this.newMessageStatus;
    }

    public synchronized void SetNewMessageStatus(boolean bool) {
        this.newMessageStatus = bool;
    }

    public synchronized boolean GetConnectedStatus() {
        return this.connectedStatus;
    }

    public synchronized void SetConnectedStatus(boolean bool) {
        this.connectedStatus = bool;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton || e.getSource() == text) {
            MessageObject message= new MessageObject(text.getText());
            controller.SendObject(message);
            this.newMessageStatus = true;
            this.text.setText("");
            textarea.setCaretPosition(textarea.getDocument().getLength());
        }
        if (e.getSource() == logoutButton) {
            logoutButton.setVisible(false);
            topLabel.setText("Logged Out");
            this.SetConnectedStatus(false);

        }
    }
}