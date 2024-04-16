package com.book_app.tictactoe.mvp.models;

import android.util.Pair;

import com.book_app.tictactoe.client.connection.Packet;
import com.book_app.tictactoe.client.connection.GameConnectionHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;

import java.util.ArrayList;


public class ServerRoomsModel {


    public Pair<ArrayList<String>, ArrayList<String>> roomsRequest(){



        GameConnectionHandler.sendPacket(new Packet().setCommand("get-rooms"));

        JsonStreamParser parser = new JsonStreamParser(GameConnectionHandler.receiveMessage());

        if(!parser.hasNext())
            return new Pair<>(new ArrayList<>(), new ArrayList<>());

        JsonObject element = parser.next().getAsJsonObject();

        JsonArray roomNameArray = element.getAsJsonArray("rooms-names");
        JsonArray connCountArray = element.getAsJsonArray("conn-count");

        ArrayList<String> rooms = new ArrayList<>();
        ArrayList<String> connectionCount = new ArrayList<>();

        if(roomNameArray.size() == connCountArray.size())
            for(int i = 0; i < roomNameArray.size(); ++i){
                rooms.add(roomNameArray.get(i).getAsString());
                connectionCount.add(connCountArray.get(i).getAsString());
            }

        return new Pair<>(rooms, connectionCount);
    }

    public String pickRoomRequest(String roomName){

        GameConnectionHandler.sendPacket(new Packet().setCommand("pick-room").setRoomName(roomName));


        return GameConnectionHandler.receiveMessage();
    }




    public boolean isConnected(){
        return GameConnectionHandler.isConnected();
    }






}
