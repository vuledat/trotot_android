package com.datvl.trotot.model;

public class Message {
    private String content;
    private String time;

    public Message(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public Message(String content) {
        this.content = content;
    }

    public Message() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
