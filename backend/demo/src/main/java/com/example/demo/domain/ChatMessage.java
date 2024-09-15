package com.example.demo.domain;

public class ChatMessage {
    private String content;
    private String conversationID;

    public ChatMessage(){};
    public ChatMessage(String content) {
        this.content = content;
    }
    public ChatMessage(String content, String conversationID) {
        this.content = content;
        this.conversationID = conversationID;
    }

    public String getContent(){
        return this.content;
    }

    public String getConversationID(){
        return this.conversationID;
    }

    public void setConversationID(String conversationID){
        this.conversationID = conversationID;
    }
}

