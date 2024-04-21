package com.book_app.tictactoe.mvp.views;

import com.book_app.tictactoe.mvp.models.RoomModel;

import java.util.ArrayList;

public interface ServerRoomsView {


    void showFailConnectToServerResultOnUI();
    void showRoomsOnUI(ArrayList<RoomModel> models);
    void updateRoomsOnUI(ArrayList<RoomModel> models);

    void showPickRoomResultOnUI(boolean isRoomPicked);





}
