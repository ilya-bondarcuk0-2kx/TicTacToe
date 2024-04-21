package com.book_app.tictactoe.client.connection;


import com.book_app.tictactoe.game.sides.Side;

public class Packet {



    private String command, pickedRoom, side , warning;




    private int id;
    private int turn;

    public int getId() {
        return id;
    }

    public Packet setId(int id) {
        this.id = id;
        return this;
    }

    private int index;



    public Packet(){

    }

    public Packet(String command,String pickedRoom, Side side, int turn, int index){
        this.command = command;
        this.pickedRoom = pickedRoom;
        this.side = side.toString();
        this.turn = turn;
        this.index = index;
    }

    public Packet(Side side, int turn, int index) {
        this.side = side.toString();
        this.turn = turn;
        this.index = index;
    }

    public Packet setCommand(String command){
        this.command = command;
        return this;
    }

    public Packet setRoomName(String roomName) {
        this.pickedRoom = roomName;
        return this;
    }

    public Packet setSide(Side side){
        this.side = side.toString();
        return this;
    }

    public Packet setTurn(int turn){
        this.turn = turn;
        return this;
    }

    public String getCommand() {
        return command;
    }

    public String getRoomName() {
        return pickedRoom;
    }

    public String getSide() {
        return side.toString();
    }

    public int getTurn() {
        return turn;
    }

    public int getIndex() {
        return index;
    }

    public String getWarning() {
        return warning;
    }

    public Packet setWarning(String warning) {
        this.warning = warning;
        return this;
    }

    public Packet setIndex(int index) {
        this.index = index;
        return this;
    }
}