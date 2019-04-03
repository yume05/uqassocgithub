package com.example.uqassoc.models;

public class Events {
    public String title;
    public int image;
    public String id;
    public String description;
    public Events(String id, String title, int image, String description ){
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
    }
}
