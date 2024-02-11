package org.example.client;


import org.example.server.Server;

public class Client {
    private View view;
    private String login;
    private Server server;
    private boolean connected;

    public Client(View view, Server server) {
        this.view = view;
        this.server = server;
    }

    public boolean connectToServer(String login){
        this.login = login;
        if (server.connectUser(this)){
            showOnWindow("Вы успешно подключились!\n");
            connected = true;
            String log = server.getLog();
            if (log != null){
                showOnWindow(log);
            }
            return true;
        } else {
            showOnWindow("Подключение не удалось");
            return false;
        }
    }

    public void disconnectFromServer(){
        if (connected) {
            connected = false;
            server.disconnectUser(this);
            view.disconnectedFromServer();
            showOnWindow("Вы были отключены от сервера!");
        }
    }

    public void answerFromServer(String messageFromServer){
        showOnWindow(messageFromServer);
    }

    public void sendMessage(String message){
        if (connected) {
            if (!message.isEmpty()) {
                server.sendMail(login + ": " + message);
            }
        } else {
            showOnWindow("Нет подключения к серверу");
        }
    }

    public String getLogin() {
        return login;
    }

    private void showOnWindow(String text) {
        view.sendMessage(text + "\n");
    }
}