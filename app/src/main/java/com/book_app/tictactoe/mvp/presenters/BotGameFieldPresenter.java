package com.book_app.tictactoe.mvp.presenters;

import com.book_app.tictactoe.game.bot.Bot;
import com.book_app.tictactoe.game.bot.EasyBot;
import com.book_app.tictactoe.game.bot.HardBot;
import com.book_app.tictactoe.game.bot.ImpossibleBot;
import com.book_app.tictactoe.game.bot.MediumBot;
import com.book_app.tictactoe.game.enums.CheckResult;
import com.book_app.tictactoe.mvp.models.BotGameFieldModel;
import com.book_app.tictactoe.mvp.presenters.abstractpresenter.Presenter;
import com.book_app.tictactoe.mvp.views.GameFieldView;

public class BotGameFieldPresenter extends Presenter<BotGameFieldModel, GameFieldView> {



    private Bot bot;

    public void updateCell(int index){
        model.updateCell(index);

        CheckResult result = model.getCheckResult();
        view().updateFieldOnUI(result, index, model.getUpdatedCellSide(index), model.getWinCombination());
        if(result == CheckResult.GAME_CONTINUE) {
            int botChoose = bot.analyzeGameField(model.getGameField());
            model.updateCell(botChoose);
            view().updateFieldOnUI(model.getCheckResult(), botChoose, model.getUpdatedCellSide(botChoose), model.getWinCombination());
        }
    }

    public void setBot(String difficulty) {

        switch (difficulty){
            case "es":
                bot = new EasyBot();
                break;
            case "md":
                bot = new MediumBot();
                break;
            case "hd":
                bot = new HardBot();
                break;
            case "im":
                bot = new ImpossibleBot();
                break;
        }
    }




    @Override
    protected void updateView() {

    }
}
