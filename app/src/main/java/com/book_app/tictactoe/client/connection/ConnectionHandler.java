package com.book_app.tictactoe.client.connection;


import android.util.Log;

import com.book_app.tictactoe.game.sides.Cross;
import com.book_app.tictactoe.game.sides.Zero;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;


public class ConnectionHandler {



    public static class ServerConnection {

        public static void connectToServer(String host, int port){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    socket = new Socket();

                    try{
                        socket.connect(new InetSocketAddress(host, port), 300);
                        isConnected = true;
                    }
                    catch (IOException e) {
                        Log.w("IOException", Objects.requireNonNull(e.getMessage()));
                    }
                }
            }).start();
        }

        public static void disconnectFromServer() {

            if(isConnected()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    Log.w("IOException", Objects.requireNonNull(e.getMessage()));
                }
            }
        }
    }

    private static Socket socket;



    private static boolean isConnected = false;




    public static boolean sendPacket(Packet packet){
        try {
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            Gson gson = new Gson();
            writer.write(gson.toJson(packet) + "\n");
            writer.flush();
            return true;
        } catch (IOException e) {
            Log.w("IOException", Objects.requireNonNull(e.getMessage()));
        }

        return false;
    }


    public static Packet receivePacket(){
        try {

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            JsonStreamParser parser = new JsonStreamParser(bufferedReader.readLine());
            JsonElement element = parser.next();
            if(element.isJsonObject()) {

                JsonObject json = element.getAsJsonObject();

                if (json != null) {


                    if (json.has("warning")) {
                        String warning = json.get("warning").getAsString();
                        return new Packet().setWarning(warning);
                    }
                    return new Packet(json.get("side").getAsString().equals("Cross") ? new Cross() : new Zero(), json.get("turn").getAsInt(), json.get("index").getAsInt());
                }
            }
        } catch (IOException e) {
            Log.w("IOException", Objects.requireNonNull(e.getMessage()));
        }
        return new Packet().setWarning("NullPacket");
    }


    public static String receiveMessage(){
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            return bufferedReader.readLine();


        } catch (IOException e) {
            Log.w("IOException", Objects.requireNonNull(e.getMessage()));
            return "";
        }


    }

    public static boolean isConnected(){
        return isConnected;
    }



}
