package com.book_app.tictactoe.mvp.presenters;

import com.book_app.tictactoe.mvp.models.GameFieldModel;
import com.book_app.tictactoe.mvp.presenters.abstractpresenter.Presenter;
import com.book_app.tictactoe.mvp.views.GameFieldView;

public class GameFieldPresenter extends Presenter<GameFieldModel, GameFieldView> {



    public void updateCell(int index){
        model.updateCell(index);
        view().updateFieldOnUI(model.getCheckResult(), index, model.getUpdatedCellSide(index), model.getWinCombination());
    }




    @Override
    protected void updateView() {

    }

}
