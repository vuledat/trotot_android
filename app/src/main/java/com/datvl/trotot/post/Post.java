package com.datvl.trotot.post;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
    private int id;
    private String name;
    private int price;
    private String image;
    private String content;
    private Date time;
    private String address;


    public Post(int id, String name, int price, String image, String content, String address, Date time) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.content = content;
        this.address = address;
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}