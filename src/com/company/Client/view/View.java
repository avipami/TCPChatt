package com.company.Client.view;

import com.company.Client.controller.*;
import com.company.Client.model.Model;
import com.company.Server.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static javax.swing.ScrollPaneConstants.*;


public class View extends JFrame {
    //Controller controller = new Controller();
    JPanel top = new JPanel();
    JPanel center = new JPanel();
    JPanel bottom = new JPanel();

    TopLabel topLabel = TopLabel.getInstance();
    DisconnectButton logoutButton = DisconnectButton.getInstance();
    MTextField text = MTextField.getInstance();
    MSendButton sendButton = MSendButton.getInstance();
    MTextArea textarea = MTextArea.getInstance();
    JScrollPane scrollPane = new JScrollPane();


    private final boolean newMessageStatus = false;
    private final boolean connectedStatus = false;
    String inText;

    public View(Model user) {
        this.setTitle("Chattaway");
        topLabel.setText(user.GetName() + " chattar");

        logoutButton.addActionListener(DisconnectButton.getInstance());
        top.add(logoutButton);
        top.add(topLabel);

        textarea.setEditable(false);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.add(textarea);
        center.add(scrollPane);

        sendButton.addActionListener(MSendButton.getInstance());
        text.addActionListener(MTextField.getInstance());
        bottom.add(text);
        bottom.add(sendButton);

        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);
        MTextField.getInstance().requestFocusInWindow();
        setSize(800, 600);
        setLocation(0, 0);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                MTextField.getInstance().requestFocus();
            }
        });
    }
}