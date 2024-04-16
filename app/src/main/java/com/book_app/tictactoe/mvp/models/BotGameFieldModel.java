package com.book_app.tictactoe.mvp.models;

import com.book_app.tictactoe.game.gamecontroller.GameField;
import com.book_app.tictactoe.game.bot.Bot;
import com.book_app.tictactoe.game.enums.CheckResult;
import com.book_app.tictactoe.game.sides.Side;

import java.util.ArrayList;

public class BotGameFieldModel {

    private final GameField gameField;

    private final Bot bot;



    public ArrayList<Integer> getWinCombination(){
        return gameField.getWinCombination();
    }

    public BotGameFieldModel(Bot bot){
        gameField = new GameField();
        this.bot = bot;
    }



    public void updateCell(int index){
        gameField.updateCell(index);
        gameField.updateCell(bot.analyzeGameField(gameField));
    }

    public CheckResult getCheckResult(){
        return gameField.checkField();
    }

    public Side getUpdatedCellSide(int index) {

        return gameField.getCell(index).getSide();

    }
}
