package com.book_app.tictactoe.mvp.models;

import com.book_app.tictactoe.client.connection.Client;
import com.book_app.tictactoe.client.connection.Packet;
import com.book_app.tictactoe.client.connection.GameConnectionHandler;
import com.book_app.tictactoe.game.enums.CheckResult;
import com.book_app.tictactoe.game.gamecontroller.GameField;
import com.book_app.tictactoe.game.sides.Side;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerGameFieldModel {

    private final GameField gameField;


    public ServerGameFieldModel(){
        gameField = new GameField();
    }

    public void updateCell(Side side,int index){
        gameField.updateCell(index, side);
    }


    public CheckResult checkField(Side side, int turn){
        return gameField.checkField(side, turn);
    }

    public AtomicBoolean isGameOver(){
        return gameField.getIsGameOver();
    }


    public void sendPacket(int index) {
        GameConnectionHandler.sendPacket(new Packet()
                .setCommand("turn")
                .setRoomName(Client.getPickedRoom())
                .setSide(Client.getSide())
                .setIndex(index));
    }

    public boolean sendPickedSide(){

        return GameConnectionHandler.sendPacket(new Packet()
                .setCommand("pick-side")
                .setRoomName(Client.getPickedRoom())
                .setSide(Client.getSide()));

    }

    public boolean quitFromRoom(){
        return GameConnectionHandler.sendPacket(new Packet()
                .setCommand("quit")
                .setRoomName(Client.getPickedRoom()));
    }

    public Packet receivePacket(){
        return GameConnectionHandler.receivePacket();
    }


    public ArrayList<Integer> getWinCombination(){
        return gameField.getWinCombination();
    }




    
}
