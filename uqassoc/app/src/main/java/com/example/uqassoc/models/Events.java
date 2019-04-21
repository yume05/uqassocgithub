package com.example.uqassoc.models;

public class Events {
    public String title;
    public int image;
    public int id;
    public String description;
    public String dateDebut;
    public String dateFin;
    public Events(int id, String title,  String description, int image, String dateDebut, String dateFin){
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
}
