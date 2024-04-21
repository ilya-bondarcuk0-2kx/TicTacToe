package com.book_app.tictactoe.game.bot;

import com.book_app.tictactoe.game.gamecontroller.GameField;

public class ImpossibleBot extends Bot{
    @Override
    public int analyzeGameField(GameField gameField) {
        return 2;
    }
}
