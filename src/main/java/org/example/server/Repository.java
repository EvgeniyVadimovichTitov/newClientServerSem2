package org.example.server;

public interface Repository {
    void write(String log);
    String read();

}
