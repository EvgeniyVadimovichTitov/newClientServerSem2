package org.example.server;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ServerWindow extends JFrame implements ServerView{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private JButton btnStart, btnStop;
    private JTextArea log;
    Server server;
    public ServerWindow(Repository repository){
        server = new Server(this, repository);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        createPanel();

        setVisible(true);
    }
    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.start();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stop();
            }
        });
        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }
    @Override
    public void showMail(String text) {
        log.append(text);
    }

    public Server getConnection(){
        return server;
    }
}
