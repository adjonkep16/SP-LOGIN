package com.example.splogin.models;

public class Event {
     String title;
     int eventID;
     String description;
     String date;
     String location;
     String Image;
     String host;
     String category;
     String author;

    public Event() {};

    public  String getAuthor() {
        return author;
    }

    public  void setAuthor(String author) {
        this.author = author;
    }

    public  String getCategory() {
        return category;
    }

    public  void setCategory(String category) {
        this.category = category;
    }


    public  String getHost() {
        return host;
    }

    public  void setHost(String host) {
        this.host = host;
    }


    public  String getTitle() {
        return title;
    }

    public  void setTitle(String title) {
        this.title = title;
    }

    public  int getEventID() {
        return eventID;
    }

    public  void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public  String getDescription() {
        return description;
    }

    public  void setDescription(String description) {
        this.description = description;
    }

    public  String getDate() {
        return date;
    }

    public  void setDate(String date) {
        this.date = date;
    }

    public  String getLocation() {
        return location;
    }

    public  void setLocation(String location) {
        this.location = location;
    }

    public  String getImage() {
        return Image;
    }

    public  void setImage(String Image) {
        this.Image = Image;
    }

    public Event(String title, int eventID, String date, String location, String Image, String category, String host, String author, String description){
        this.title=title;
        this.eventID=eventID;
        this.description=description;
        this.date=date;
        this.location=location;
        this.Image=Image;
        this.category=category;
        this.author=author;
        this.host=host;
    }

}
