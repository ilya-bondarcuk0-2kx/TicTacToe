package com.book_app.tictactoe.mvp.views;

import java.util.ArrayList;

public interface ServerRoomsView {


    void showFailConnectToServerResultOnUI();
    void showRoomsOnUI(ArrayList<String> roomNames, ArrayList<String> connCount);
    void updateRoomsOnUI(ArrayList<String> updRooms, ArrayList<String> connCount);

    void showPickRoomResultOnUI(boolean isRoomPicked);





}
