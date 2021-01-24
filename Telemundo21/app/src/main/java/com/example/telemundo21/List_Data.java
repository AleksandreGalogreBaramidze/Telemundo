package com.example.telemundo21;

public class List_Data {
    private String id;
    private String name;
    private String movie;

    public  List_Data(){


    }

    public List_Data(String id, String name, String movie) {
        this.id = id;
        this.name = name;
        this.movie = movie;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMovie() {
        return movie;
    }
}