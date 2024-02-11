package org.example.server;

import org.example.client.Client;

import java.util.ArrayList;
import java.util.List;

public class Server {

    private boolean work;
    private List<Client> clientList;
    private ServerView serverView;
    private Repository repository;

    public Server(ServerView serverView, Repository repository) {
        this.work = false;
        this.clientList = new ArrayList<>();
        this.serverView = serverView;
        this.repository = repository;
    }
    public void start() {
        if (work) {
            show("Сервер уже был запущен");
        } else {
            work = true;
            show("Сервер запущен!");
        }
    }

    public void stop () {
            if (!work) {
                show("Сервер уже был остановлен");
            } else {
                work = false;
                while (!clientList.isEmpty()) {
                    disconnectUser(clientList.get(clientList.size() - 1));
                }
                show("Сервер остановлен!");
            }
        }
    public void disconnectUser(Client client){
        clientList.remove(client);
        if (client != null){
            client.disconnectFromServer();
        }
        serverView.showMail(client.getLogin()+ " disconnect from chat");
    }

    public boolean connectUser(Client client){
        if (!work){
            return false;
        }
        clientList.add(client);
        serverView.showMail(client.getLogin()+ " connect from chat");
        return true;
    }
    public void sendMail(String text){
        if (!work){
            return;
        }
        show(text);
        answerAll(text);
        saveInLog(text);
    }
    private void answerAll(String text){
        for (Client client: clientList){
            client.answerFromServer(text);
        }
    }
    public String getLog() {
        return repository.read();
    }
    public void saveInLog(String text){
        repository.write(text);
    }
    private void show(String text){
        serverView.showMail(text + "\n");
    }


}
