package com.book_app.tictactoe.mvp.models;

public class RoomModel {


    private String name, connCount;

    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConnCount() {
        return connCount;
    }

    public void setConnCount(String connCount) {
        this.connCount = connCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomModel(String name, String connCount, int id) {
        this.name = name;
        this.connCount = connCount;
        this.id = id;
    }
    public RoomModel(){

    }
}
