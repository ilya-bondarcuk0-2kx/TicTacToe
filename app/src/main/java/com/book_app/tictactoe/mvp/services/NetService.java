package com.book_app.tictactoe.mvp.services;


import com.book_app.tictactoe.client.connection.Packet;

import java.net.Socket;

public interface NetService {

    boolean sendPacket(Socket conn);
    Packet receivePacket(Socket conn);
    String receiveMessage(Socket conn);

}
