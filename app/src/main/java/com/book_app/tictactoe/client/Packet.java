package com.book_app.tictactoe.client;

public class Packet {



    public Packet(String side, int index, int turn){
        this.side = side;
        this.index = index;
        this.turn = turn;
    }

    public String getSide() {
        return side;
    }


    public int getIndex() {
        return index;
    }



    public int getTurn() {
        return turn;
    }


    String picked_room, side;
    int index, turn;

}