package com.example.demo.domain;

import java.util.HashMap;

public interface ClientsRepository {
    HashMap<String, Client> findAll();
    Client findOne(String sessionID);
    void addOne(String sessionID);
    void deleteOne(String sessionID);
    void removeChat(String sessionID);
}
