package com.book_app.tictactoe.mvp.presenters;

import com.book_app.tictactoe.mvp.models.BotGameFieldModel;
import com.book_app.tictactoe.mvp.presenters.abstractpresenter.Presenter;
import com.book_app.tictactoe.mvp.views.GameFieldView;

public class BotGameFieldPresenter extends Presenter<BotGameFieldModel, GameFieldView> {


    public void updateCell(int index){
        model.updateCell(index);
        view().updateFieldOnUI(model.getCheckResult(), index, model.getUpdatedCellSide(index), model.getWinCombination());
    }


    @Override
    protected void updateView() {

    }
}
