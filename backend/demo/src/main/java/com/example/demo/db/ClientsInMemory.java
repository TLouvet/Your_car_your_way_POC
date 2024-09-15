package com.example.demo.db;

import com.example.demo.domain.Client;
import com.example.demo.domain.ClientsRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;


@Repository
public class ClientsInMemory implements ClientsRepository {

    private final HashMap<String, Client> clients = new HashMap<String, Client>();

    public HashMap<String, Client> findAll(){
        return this.clients;
    }

    public Client findOne(String sessionID){
        return this.clients.get(sessionID);
    }

    public void addOne(String sessionID){
        this.clients.put(sessionID, new Client());
    }

    public void deleteOne(String sessionID) {
        this.clients.remove(sessionID);
    }

    public void removeChat(String sessionID){
        this.clients.forEach(($, client) -> {
            if (client.getChatsWith().equals(sessionID)){
                client.setChatsWith(null);
            }
        });
    }
}
