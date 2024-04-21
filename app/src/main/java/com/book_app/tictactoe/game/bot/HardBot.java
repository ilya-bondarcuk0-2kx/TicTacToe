package com.book_app.tictactoe.game.bot;

import com.book_app.tictactoe.game.enums.CheckResult;
import com.book_app.tictactoe.game.gamecontroller.GameField;

public class HardBot extends Bot{

    @Override
    public int analyzeGameField(GameField gameField) {





        return 0;
    }


    CheckResult getPredict(GameField gameField){


        return CheckResult.GAME_CONTINUE;
    }




}
