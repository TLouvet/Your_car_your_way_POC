package com.example.demo.domain;

import java.util.HashSet;
import java.util.Set;

public class Employee {
    private Set<String> chats = new HashSet<String>();

    public boolean chatsWith(String id){
        return this.chats.contains(id);
    }

    public void removeFromChat(String id){
        this.chats.remove(id);
    }

    public void addToChat(String id){
        this.chats.add(id);
    }
}
