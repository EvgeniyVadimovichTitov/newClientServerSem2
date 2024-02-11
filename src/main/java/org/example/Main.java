package org.example;

import org.example.client.ClientGUI;
import org.example.server.RepositoryFile;
import org.example.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow(new RepositoryFile());
        new ClientGUI(serverWindow);
        new ClientGUI(serverWindow);
    }
}