package com.book_app.tictactoe.activities.server;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.book_app.tictactoe.R;
import com.book_app.tictactoe.adaptedviews.RoomView;
import com.book_app.tictactoe.adaptedviews.VariableToast;
import com.book_app.tictactoe.client.connection.Client;
import com.book_app.tictactoe.client.connection.GameConnectionHandler;
import com.book_app.tictactoe.client.javarxadapted.DisposableManager;
import com.book_app.tictactoe.mvp.models.ServerRoomsModel;
import com.book_app.tictactoe.mvp.presenters.ServerRoomsPresenter;
import com.book_app.tictactoe.mvp.views.ServerRoomsView;
import java.util.ArrayList;


public class ServerRoomsActivity extends AppCompatActivity implements ServerRoomsView {

    private ServerRoomsPresenter presenter;
    private LinearLayout  roomViewsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_rooms);
        roomViewsLayout = findViewById(R.id.room_views_layout);
        presenter = new ServerRoomsPresenter();
        presenter.setModel(new ServerRoomsModel());
        presenter.getRooms();
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
        DisposableManager.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DisposableManager.clear();
        GameConnectionHandler.ServerConnection.disconnectFromServer();
    }

    @SuppressLint("SetTextI18n")
    private void makeRoomsViews(ArrayList<String> roomNames, ArrayList<String> roomConnections) {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

            for(int i = 0; i < roomNames.size() ; ++i){
                RoomView roomView = new RoomView(roomViewsLayout.getContext());
                roomView.setRoomName(roomNames.get(i));
                roomView.setConnCount(roomConnections.get(i));
                roomView.setLayoutParams(layoutParams);
                roomViewsLayout.addView(roomView);

            }

    }

    private void setClickListeners(){
        for(int i = 0; i < roomViewsLayout.getChildCount() ; ++i){

            View view = roomViewsLayout.getChildAt(i);

            view.setOnClickListener(l -> {
                Client.setPickedRoom(((RoomView)view).getRoomNameText());
                presenter.pickRoom(Client.getPickedRoom());
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
    public void showRoomsOnUI(ArrayList<String> roomNames, ArrayList<String> connCount) {
        makeRoomsViews(roomNames, connCount);
        setClickListeners();
    }

    @Override
    public void updateRoomsOnUI(ArrayList<String> updRooms, ArrayList<String> connCount) {

        for(int i = 0; i < roomViewsLayout.getChildCount() ;++i){
            for(int j = 0; j < updRooms.size(); ++j){
                if(((RoomView)roomViewsLayout.getChildAt(i)).getRoomNameText().equals(updRooms.get(j)))
                    ((RoomView)roomViewsLayout.getChildAt(i)).setConnCount(connCount.get(j));
            }
            /*TODO: Создать и отобразить новую комнату + листенер , или заменить навзвание существующей (думаю все же делать это в отдельном методе интерфейса)*/
        }
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