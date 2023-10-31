package com.example.splogin.models;

public class Event {
    static String title;
    static int eventID;
    static String description;
    static String date;
    static String location;
    static String Image;
    static String host;
    static String category;
    static String author;

    public static String getAuthor() {
        return author;
    }

    public static void setAuthor(String author) {
        Event.author = author;
    }

    public static String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        Event.category = category;
    }


    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        Event.host = host;
    }


    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        Event.title = title;
    }

    public static int getEventID() {
        return eventID;
    }

    public static void setEventID(int eventID) {
        Event.eventID = eventID;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Event.description = description;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        Event.date = date;
    }

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        Event.location = location;
    }

    public static String getImage() {
        return Image;
    }

    public static void setImage(String Image) {
        Event.Image = Image;
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
