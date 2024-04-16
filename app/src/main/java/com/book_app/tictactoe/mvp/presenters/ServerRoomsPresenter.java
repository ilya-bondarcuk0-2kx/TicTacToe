package com.book_app.tictactoe.mvp.presenters;

import com.book_app.tictactoe.client.javarxadapted.DisposableManager;
import com.book_app.tictactoe.mvp.models.ServerRoomsModel;
import com.book_app.tictactoe.mvp.presenters.abstractpresenter.Presenter;
import com.book_app.tictactoe.mvp.views.ServerRoomsView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ServerRoomsPresenter extends Presenter<ServerRoomsModel, ServerRoomsView> {



    public void getRooms(){
        if(model.isConnected()) {
            DisposableManager.add(Observable.fromCallable(model::roomsRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> view().showRoomsOnUI(result.first , result.second),
                           throwable -> view().showFailConnectToServerResultOnUI()));
            return;
        }

        view().showFailConnectToServerResultOnUI();
    }

    public void pickRoom(String roomName){

        if(model.isConnected()) {
            DisposableManager.add(Observable.fromCallable(() -> model.pickRoomRequest(roomName))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(message -> view().showPickRoomResultOnUI(message.equals("room-picked")),
                               throwable -> view().showFailConnectToServerResultOnUI()));
            return;
        }

        view().showFailConnectToServerResultOnUI();
    }


    @Override
    protected void updateView() {

    }
}
