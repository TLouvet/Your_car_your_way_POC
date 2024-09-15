package com.example.demo.domain;

public class Client {
    private String chatsWith;

    public Client() {
        this.chatsWith = null;
    }

    public void setChatsWith(String id){
        this.chatsWith = id;
    }

    public String getChatsWith(){
        return this.chatsWith;
    }
}
