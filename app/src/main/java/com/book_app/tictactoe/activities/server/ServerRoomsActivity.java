package com.book_app.tictactoe.activities.server;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.book_app.tictactoe.R;
import com.book_app.tictactoe.adaptedviews.RoomView;
import com.book_app.tictactoe.adaptedviews.VariableToast;
import com.book_app.tictactoe.client.connection.Client;
import com.book_app.tictactoe.client.connection.ConnectionHandler;
import com.book_app.tictactoe.client.javarxadapted.DisposableManager;
import com.book_app.tictactoe.mvp.models.RoomModel;
import com.book_app.tictactoe.mvp.presenters.ServerRoomsPresenter;
import com.book_app.tictactoe.mvp.views.ServerRoomsView;

import java.util.ArrayList;


public class ServerRoomsActivity extends AppCompatActivity implements ServerRoomsView {

    private ServerRoomsPresenter presenter;
    private LinearLayout  roomViewsLayout;

    private ImageButton loadRoomsBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_rooms);
        roomViewsLayout = findViewById(R.id.room_views_layout);
        loadRoomsBtn = findViewById(R.id.load_rooms_btn);
        presenter = new ServerRoomsPresenter();
        presenter.setModel(new RoomModel());
        presenter.bindView(this);

        loadRoomsBtn.setOnClickListener(l -> {
            roomViewsLayout.removeAllViewsInLayout();
            presenter.getRooms();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.bindView(this);


    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
        roomViewsLayout.removeAllViewsInLayout();
        DisposableManager.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        roomViewsLayout.removeAllViewsInLayout();
        DisposableManager.clear();
        ConnectionHandler.ServerConnection.disconnectFromServer();
    }

    @SuppressLint("SetTextI18n")
    private void makeRoomsViews(ArrayList<RoomModel> models) {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

            for(RoomModel model : models){
                RoomView roomView = new RoomView(roomViewsLayout.getContext());
                roomView.setRoomName(model.getName());
                roomView.setConnCount(model.getConnCount());
                roomView.setId(model.getId());
                roomView.setLayoutParams(layoutParams);
                roomViewsLayout.addView(roomView);
            }

    }

    private void setClickListeners(){
        for(int i = 0; i < roomViewsLayout.getChildCount() ; ++i){

            View view = roomViewsLayout.getChildAt(i);

            view.setOnClickListener(l -> {
                Client.setPickedRoom(((RoomView)view).getRoomNameText());
                presenter.pickRoom(((RoomView)view).getRoomNameText());
            });;

        }
    }

    private void setListenersToNull() {

        for(int i = 0; i < roomViewsLayout.getChildCount() ; ++i){

            roomViewsLayout.getChildAt(i).setOnClickListener(null);

        }
    }

    @Override
    public void showFailConnectToServerResultOnUI() {

        TextView connectionFailedView = new TextView(this);
        connectionFailedView.setText("Сервер не отвечает или интернет соединение нестабильно(отсутсвует)");
        connectionFailedView.setTextSize(20);
        connectionFailedView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        connectionFailedView.setLayoutParams(layoutParams);
        setContentView(connectionFailedView);

    }

    @Override
    public void showRoomsOnUI(ArrayList<RoomModel> models) {
        makeRoomsViews(models);
        setClickListeners();
    }

    @Override
    public void updateRoomsOnUI(ArrayList<RoomModel> models) {

        /*for(int i = 0; i < roomViewsLayout.getChildCount() ;++i){
            for(int j = 0; j < updRooms.size(); ++j){
                if(((RoomView)roomViewsLayout.getChildAt(i)).getRoomNameText().equals(updRooms.get(j)))
                    ((RoomView)roomViewsLayout.getChildAt(i)).setConnCount(connCount.get(j));
            }*/
            /*TODO: Создать и отобразить новую комнату + листенер , или заменить навзвание существующей (думаю все же делать это в отдельном методе интерфейса)*/
        /*}*/
    }

    @Override
    public void showPickRoomResultOnUI(boolean isRoomPicked) {

        if(!isRoomPicked){
            VariableToast.makeText(this, "Комната заполнена", Toast.LENGTH_SHORT).show();
            return;
        }
        DisposableManager.clear();
        startActivity(new Intent(this, ServerGameActivity.class));
    }
}