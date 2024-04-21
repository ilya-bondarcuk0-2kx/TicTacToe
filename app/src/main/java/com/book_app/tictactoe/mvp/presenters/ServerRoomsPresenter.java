package com.book_app.tictactoe.mvp.presenters;

import com.book_app.tictactoe.client.connection.ConnectionHandler;
import com.book_app.tictactoe.client.javarxadapted.DisposableManager;
import com.book_app.tictactoe.mvp.models.RoomModel;
import com.book_app.tictactoe.mvp.presenters.abstractpresenter.Presenter;
import com.book_app.tictactoe.mvp.services.RoomService;
import com.book_app.tictactoe.mvp.views.ServerRoomsView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ServerRoomsPresenter extends Presenter<RoomModel, ServerRoomsView> {



    public void getRooms(){
        if(ConnectionHandler.isConnected()) {
            DisposableManager.add(Observable.fromCallable(RoomService::roomsRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> view().showRoomsOnUI(result),
                           throwable -> view().showFailConnectToServerResultOnUI()));
            return;
        }

        view().showFailConnectToServerResultOnUI();
    }

    public void pickRoom(String roomName){

        if(ConnectionHandler.isConnected()) {
            DisposableManager.add(Observable.fromCallable(() -> RoomService.pickRoomRequest(roomName))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(packet -> view().showPickRoomResultOnUI(packet.getWarning().equals("room-picked")),
                               throwable -> view().showFailConnectToServerResultOnUI()));
            return;
        }

        view().showFailConnectToServerResultOnUI();
    }


    @Override
    protected void updateView() {

    }
}
