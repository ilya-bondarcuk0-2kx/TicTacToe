package com.book_app.tictactoe.adaptedviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.book_app.tictactoe.R;


public class RoomView extends ConstraintLayout {


    private TextView roomNameBtn, connCount;



    public RoomView(Context context) {
        super(context);
        init();
    }



    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.room_view, this, true);
        roomNameBtn = findViewById(R.id.roomName);
        connCount = findViewById(R.id.connCount);
    }
    public void setRoomName(String name) {
        roomNameBtn.setText(name);
    }

    public void setConnCount(String count) {

        connCount.setText(String.format("%s/2", count));
    }

    public String getRoomNameText(){

        return roomNameBtn.getText().toString();
    }


    public String getConnCountText(){
        return connCount.getText().toString();
    }
}