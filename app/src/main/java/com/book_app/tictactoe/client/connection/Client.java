package com.book_app.tictactoe.client.connection;

import com.book_app.tictactoe.game.sides.Side;

public class Client {


    private static String pickedRoom;
    private static Side side;


    public static void setSide(Side selectedSide) {
        side = selectedSide;
    }

    public static Side getSide() {
        return side;
    }

    public static String getPickedRoom() {
        return pickedRoom;
    }

    public static void setPickedRoom(String pickedRoom) {
        Client.pickedRoom = pickedRoom;
    }
}
