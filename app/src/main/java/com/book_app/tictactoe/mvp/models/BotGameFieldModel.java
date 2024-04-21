package com.book_app.tictactoe.mvp.models;

import com.book_app.tictactoe.game.enums.CheckResult;
import com.book_app.tictactoe.game.gamecontroller.GameField;
import com.book_app.tictactoe.game.sides.Side;

import java.util.ArrayList;

public class BotGameFieldModel {

    private final GameField gameField;





    public ArrayList<Integer> getWinCombination(){
        return gameField.getWinCombination();
    }

    public GameField getGameField() {
        return gameField;
    }

    public BotGameFieldModel(){
        gameField = new GameField();
    }



    public void updateCell(int index){
        gameField.updateCell(index);
    }

    public CheckResult getCheckResult(){
        return gameField.checkField();
    }

    public Side getUpdatedCellSide(int index) {

        return gameField.getCell(index).getSide();

    }
}
