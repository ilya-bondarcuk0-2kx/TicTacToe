package com.book_app.tictactoe.client;


import android.net.wifi.WifiManager;
import android.system.VmSocketAddress;
import android.widget.Toast;

import com.book_app.tictactoe.GameObjects.Side;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;


public class Client {


    private Packet packet;

    private Socket socket;

    private final Side side;


    private String msg;

    public Client(Side side){
        this.side = side;
    }




    public boolean connectToServer(String host, int port){

        try {
            socket = new Socket();
            socket.connect( new InetSocketAddress(host, port), 200); // Устанавливаем таймаут на подключение
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }

    public boolean isConnected(){
        return socket.isConnected();
    }

    public void disconnectFromServer(){
        try {
            if(socket != null) {
                sendMessage("quit");
                if(socket.isConnected()){
                socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void sendMessage(String msg){

        if(socket == null)
            return;
        else if(socket.isClosed() || !socket.isConnected())
            return;

        try {

            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            writer.write(msg);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveMessage(){
        if(socket == null)
            return;

        try {
            if (socket.isClosed() || !socket.isConnected()) {
                return;
            }
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bfReader = new BufferedReader(inputReader);

             msg = bfReader.readLine();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMsg(){
        sendMessage(side.toString());
        receiveMessage();
        return msg;
    }
    public void sendPacket(Packet packet){

        if(socket == null)
            return;
        else if(socket.isClosed() || !socket.isConnected())
            return;

        try {

            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

            Gson gson = new Gson();
            String json = gson.toJson(packet);
            writer.write(json);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ReceiveResult  receivePacket(){

        if(socket == null)
            return ReceiveResult.SOCKET_ERROR;

        try {
            if(socket.isClosed() || !socket.isConnected()) {
                packet = null;
                return ReceiveResult.PACKET_NULL;
            }
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bfReader = new BufferedReader(inputReader);


            String response = bfReader.readLine();


            switch (response) {
                case "null":
                    packet = null;
                    return ReceiveResult.PACKET_NULL;
                case "disconnect":
                    return ReceiveResult.OPPONENT_DISCONNECT;
                case "wait":
                    return ReceiveResult.WAIT;
                case "connected":
                    return ReceiveResult.OPPONENT_CONNECTED;
            }

            JsonStreamParser parser = new JsonStreamParser(response);

            JsonObject json = parser.next().getAsJsonObject();


            this.packet = new Packet(json.get("side").getAsString(), json.get("index").getAsInt(), json.get("turn").getAsInt());

        } catch (IOException e) {
            e.printStackTrace();
            return ReceiveResult.EXCEPTION;
        }
        return ReceiveResult.OK;

    }
    public Packet getPacket(){
        return packet;
    }

    public Side getSide(){
        return side;
    }

}
