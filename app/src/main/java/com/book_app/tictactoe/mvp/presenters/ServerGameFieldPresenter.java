package com.book_app.tictactoe.mvp.presenters;

import com.book_app.tictactoe.client.javarxadapted.DisposableManager;
import com.book_app.tictactoe.game.sides.Cross;
import com.book_app.tictactoe.game.sides.Side;
import com.book_app.tictactoe.game.sides.Zero;
import com.book_app.tictactoe.mvp.models.ServerGameFieldModel;
import com.book_app.tictactoe.mvp.presenters.abstractpresenter.Presenter;
import com.book_app.tictactoe.mvp.views.ServerGameFieldView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ServerGameFieldPresenter extends Presenter<ServerGameFieldModel, ServerGameFieldView> {





    @Override
    protected void updateView() {

    }



    public void updateCell(int index) {

        DisposableManager.add(Observable.fromAction(() -> model.sendPacket(index))
                .subscribeOn(Schedulers.io())
                .subscribe());

    }


    public void sendSide(){

        DisposableManager.add(Observable.fromCallable(model::sendPickedSide)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());

    }

    public void setUpReceiveProcess() {

        DisposableManager.add(Observable.fromCallable(model::receivePacket)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatUntil(() -> model.isGameOver().get())
                .subscribe(packet -> {

                    if(packet.getWarning() != null){
                        view().showWarningOnUI(packet.getWarning());
                        return;
                    }

                    Side side;

                    if(packet.getSide().equals("Cross")) side = new Cross();
                    else side = new Zero();

                    model.updateCell(side, packet.getIndex());
                    view().updateFieldOnUI(model.checkField(side, packet.getTurn()), packet.getIndex(),side, model.getWinCombination());

                }, Throwable::printStackTrace));
    }


    public void quitFromRoomAndClearResources() {
        DisposableManager.add(Observable.fromCallable(model::quitFromRoom)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(b -> {
                    DisposableManager.clear();
                }));
    }





}
