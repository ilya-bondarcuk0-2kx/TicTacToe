package com.book_app.tictactoe.mvp.services;

import com.book_app.tictactoe.client.connection.ConnectionHandler;
import com.book_app.tictactoe.client.connection.Packet;
import com.book_app.tictactoe.mvp.models.RoomModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;

import java.util.ArrayList;




public class RoomService {



    public static ArrayList<RoomModel> roomsRequest(){



        ConnectionHandler.sendPacket(new Packet().setCommand("get-rooms"));

        JsonStreamParser parser = new JsonStreamParser(ConnectionHandler.receiveMessage());

        if(!parser.hasNext())
            return new ArrayList<>();

        JsonObject element = parser.next().getAsJsonObject();

        JsonArray roomNameArray = element.getAsJsonArray("rooms-names");
        JsonArray connCountArray = element.getAsJsonArray("conn-count");
//        JsonArray idArray = element.getAsJsonArray("id");


        ArrayList<RoomModel> models = new ArrayList<>();
        if(roomNameArray.size() == connCountArray.size())
            for(int i = 0; i < roomNameArray.size(); ++i){
                RoomModel model = new RoomModel();

                model.setName(roomNameArray.get(i).getAsString());
                model.setConnCount(connCountArray.get(i).getAsString());
                models.add(model);
            }

        return models;
    }

    public static Packet pickRoomRequest(String roomName){

        ConnectionHandler.sendPacket(new Packet().setCommand("pick-room").setRoomName(roomName));


        return ConnectionHandler.receivePacket();
    }









}
